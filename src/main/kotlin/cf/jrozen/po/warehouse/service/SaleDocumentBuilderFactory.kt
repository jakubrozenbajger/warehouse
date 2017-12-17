package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.controller.SaleDocumentRequest
import cf.jrozen.po.warehouse.domain.SaleDocumentType
import org.springframework.stereotype.Component

@Component
class SaleDocumentBuilderFactory {

    fun getBuilder(saleDocumentRequest: SaleDocumentRequest): AbstractSaleDocumentBuilder {
        return when (saleDocumentRequest.type) {
            SaleDocumentType.RECEIPT -> ReceiptBuilder(saleDocumentRequest)
            SaleDocumentType.INVOICE -> InvoiceBuilder(saleDocumentRequest)
        }
    }
}