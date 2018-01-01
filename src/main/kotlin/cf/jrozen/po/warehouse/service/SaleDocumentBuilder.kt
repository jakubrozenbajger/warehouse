package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.*
import cf.jrozen.po.warehouse.utils.randomUUID
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

interface SaleDocumentBuilder {
    fun build(): SaleDocument
}


abstract class AbstractSaleDocumentBuilder(
        val companyService: CompanyService,
        val saleDocumentRequest: SaleDocumentRequest
) : SaleDocumentBuilder {

    lateinit var dealer: Dealer
    lateinit var order: Order

    open fun fromOrder(order: Order): AbstractSaleDocumentBuilder {
        this.order = order
        return this
    }

    fun withCreator(dealer: Dealer): AbstractSaleDocumentBuilder {
        this.dealer = dealer
        return this
    }

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

    override fun build(): SaleDocument {
        return Invoice(
                randomUUID(),
                saleDocumentRequest.creationDate ?: LocalDateTime.now(),
                saleDocumentRequest.paymentDate,
                companyService.getSellerInfo(),
                getInfoAsString(order.customer),
                order.customer.address,
                order,
                order.customer,
                dealer,
                ArrayList(),
                saleDocumentService.nextSerialNumber()
        )
    }

}

class ReceiptBuilder(
        companyService: CompanyService,
        saleDocumentRequest: SaleDocumentRequest
) : AbstractSaleDocumentBuilder(companyService, saleDocumentRequest) {

    override fun build(): SaleDocument {
        return Receipt(
                randomUUID(),
                saleDocumentRequest.creationDate ?: LocalDateTime.now(),
                saleDocumentRequest.paymentDate,
                companyService.getSellerInfo(),
                getInfoAsString(order.customer),
                order.customer.address,
                order,
                order.customer,
                dealer,
                ArrayList()
        )
    }

}