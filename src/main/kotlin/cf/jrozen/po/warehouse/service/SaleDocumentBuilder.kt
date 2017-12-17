package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.controller.SaleDocumentRequest
import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.domain.SaleDocument
import cf.jrozen.po.warehouse.domain.User

interface SaleDocumentBuilder {
    fun build(): SaleDocument
}


abstract class AbstractSaleDocumentBuilder(
        val saleDocumentRequest: SaleDocumentRequest
) : SaleDocumentBuilder {
    lateinit var creator: User

    abstract fun fromOrder(order: Order): AbstractSaleDocumentBuilder

    fun withCreator(user: User): AbstractSaleDocumentBuilder {
        this.creator = user
        return this
    }
}

class InvoiceBuilder(saleDocumentRequest: SaleDocumentRequest) : AbstractSaleDocumentBuilder(saleDocumentRequest) {

    override fun fromOrder(order: Order): InvoiceBuilder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun build(): SaleDocument {
        TODO("not implemented")
    }
}

class ReceiptBuilder(
        saleDocumentRequest: SaleDocumentRequest
) : AbstractSaleDocumentBuilder(saleDocumentRequest) {

    override fun fromOrder(order: Order): ReceiptBuilder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun build(): SaleDocument {
        TODO("not implemented")
    }

}