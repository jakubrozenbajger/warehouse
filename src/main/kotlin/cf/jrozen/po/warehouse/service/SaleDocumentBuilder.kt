package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.*
import cf.jrozen.po.warehouse.utils.randomUUID
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.HashSet

interface SaleDocumentBuilder {
    fun build(): SaleDocument
}


abstract class AbstractSaleDocumentBuilder(
        val companyService: CompanyService,
        val saleDocumentRequest: SaleDocumentRequest
) : SaleDocumentBuilder {

    lateinit var dealer: Dealer
    lateinit var order: Order

    /**
     * Assigns the given [order] to the class field named order.
     * @return current order.
     */
    open fun fromOrder(order: Order): AbstractSaleDocumentBuilder {
        this.order = order
        return this
    }

    /**
     * Assigns the given [dealer] to the class field named dealer.
     * @return current dealer.
     */
    fun withCreator(dealer: Dealer): AbstractSaleDocumentBuilder {
        this.dealer = dealer
        return this
    }

    /**
     * Transforms the given [customer]'s data into a string.
     * @return the given customer's data in the form of a string.
     */
    fun getInfoAsString(customer: Customer): String {
        val sj = StringJoiner(", ")
        sj.add(customer.name)
        customer.nip?.let { sj.add(it) }
        sj.add(customer.address.asString())
        sj.add(customer.phoneNumber)
        return sj.toString()
    }
}

class InvoiceBuilder(
        companyService: CompanyService,
        private val saleDocumentService: SaleDocumentService,
        saleDocumentRequest: SaleDocumentRequest
) : AbstractSaleDocumentBuilder(companyService, saleDocumentRequest) {

    /**
     * Fills the invoice with data.
     * @return invoice supplemented with data.
     */
    override fun build(): SaleDocument {
        return Invoice(
                randomUUID(),
                saleDocumentRequest.creationDate ?: LocalDateTime.now(),
                saleDocumentRequest.paymentDate,
                companyService.getSellerInfo(),
                getInfoAsString(order.customer),
                saleDocumentRequest.description,
                order.customer.address,
                order,
                order.customer,
                dealer,
                HashSet(),
                saleDocumentService.nextSerialNumber()
        )
    }

}

class ReceiptBuilder(
        companyService: CompanyService,
        saleDocumentRequest: SaleDocumentRequest
) : AbstractSaleDocumentBuilder(companyService, saleDocumentRequest) {

    /**
     * Fills the receipt with data.
     * @return receipt supplemented with data.
     */
    override fun build(): SaleDocument {
        return Receipt(
                randomUUID(),
                saleDocumentRequest.creationDate ?: LocalDateTime.now(),
                saleDocumentRequest.paymentDate,
                companyService.getSellerInfo(),
                getInfoAsString(order.customer),
                saleDocumentRequest.description,
                order.customer.address,
                order,
                order.customer,
                dealer,
                HashSet()
        )
    }

}