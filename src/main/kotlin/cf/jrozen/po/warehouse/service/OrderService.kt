package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.DocumentState
import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.domain.SaleDocumentType
import cf.jrozen.po.warehouse.repository.OrderRepository
import cf.jrozen.po.warehouse.repository.SaleDocumentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

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
     * @return the order with the given id number.
     */
    @Transactional(readOnly = true)
    fun getOrder(orderId: String): Order = orderRepository.getOne(orderId)

    @Transactional(readOnly = true)
    fun getOrderSum(orderId: String): BigDecimal {
        val order = getOrder(orderId)
        return financeProcessingStrategy.calculateGrossPrice(order)
    }

    /**
     * Updates changes in a given [order].
     * @return the given order after the change.
     */
    fun updateOrder(order: Order): Order = orderRepository.save(order)

    /**
     * Creates an order sales document with the given identification number [orderId].
     * Additional information on the type of sales document and data on the order's execution are included in [saleDocumentRequest].
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

    fun getSaleDocumentId(orderId: String): String? {
        return saleDocumentRepository.getOrdersSaleDocumentId(orderId)
    }

}