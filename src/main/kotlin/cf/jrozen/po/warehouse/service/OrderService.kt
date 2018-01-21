package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.DocumentState
import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.domain.SaleDocumentType
import cf.jrozen.po.warehouse.repository.OrderRepository
import cf.jrozen.po.warehouse.repository.SaleDocumentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

/**
 * [OrderService] allows operations on the sales document
 * @property orderRepository allows access to orders
 * @property saleDocumentService allows to create a new sales document
 * @property financeProcessingStrategy calculates the prices of goods depending on taxes
 * @property saleDocumentRepository allows access to sales documents
 */
@Service
@Transactional(readOnly = false)
class OrderService(
        val orderRepository: OrderRepository,
        val saleDocumentService: SaleDocumentService,
        val financeProcessingStrategy: FinanceProcessingStrategy,
        val saleDocumentRepository: SaleDocumentRepository
) {

    /**
     * Retrieves all orders from the repository.
     * @return list of all orders in the warehouse.
     */
    @Transactional(readOnly = true)
    fun getAllOrders(): MutableList<Order> = orderRepository.findAll()

    /**
     * Looks up the order with the id number [orderId] in the repository.
     * @param[orderId] the number that identifies the order to be returned
     * @return the order with the given id number.
     */
    @Transactional(readOnly = true)
    fun getOrder(orderId: String): Order = orderRepository.getOne(orderId)

    /**
     * Calculates the gross price of the order with id [orderId]
     * @param[orderId] the number that identifies the order to be returned
     * @return the gross price of the order
     */
    @Transactional(readOnly = true)
    fun getOrderSum(orderId: String): BigDecimal {
        val order = getOrder(orderId)
        return financeProcessingStrategy.calculateGrossPrice(order)
    }

    /**
     * Updates changes in a given [order].
     * @param[order] to be updated
     * @return the given order after the change.
     */
    fun updateOrder(order: Order): Order = orderRepository.save(order)

    /**
     * Creates an order sales document with the given identification number [orderId].
     * Additional information on the type of sales document and data on the order's execution are included in [saleDocumentRequest].
     * @param[orderId] identifying the order that will be created
     * @param[saleDocumentRequest] information on the type of sales document
     * @return the order with the given identification number.
     */
    fun createSaleDocument(orderId: String, saleDocumentRequest: SaleDocumentRequest): Order {
        val order: Order = orderRepository.getOne(orderId)
        if (!order.canGenerateSaleDocument())
            throw IllegalArgumentException("Cannot generate sale document for order: $orderId")
        if (saleDocumentRequest.type == SaleDocumentType.INVOICE
                && order.customer.nip == null)
            throw IllegalArgumentException("cannot generate sale document for order: $order, cause nip is not present")

        saleDocumentService.generateNewSaleDocument(order, saleDocumentRequest)

        order.documentState = DocumentState.REALIZED
        orderRepository.save(order)
        return order
    }

    /**
     * Retrieves order from the repository with given id
     * @param[orderId] that identifies the order
     * @return list of all orders in the warehouse.
     */
    fun getSaleDocumentId(orderId: String): String? {
        return saleDocumentRepository.getOrdersSaleDocumentId(orderId)
    }

}