package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.controller.SaleDocumentRequest
import cf.jrozen.po.warehouse.domain.SaleDocumentType
import org.springframework.stereotype.Component

@Component
class SaleDocumentBuilderFactory(
        val saleDocumentService: SaleDocumentService,
        val companyService: CompanyService
) {

    fun getBuilder(saleDocumentRequest: SaleDocumentRequest): AbstractSaleDocumentBuilder {
        return when (saleDocumentRequest.type) {
            SaleDocumentType.RECEIPT -> ReceiptBuilder(companyService, saleDocumentRequest)
            SaleDocumentType.INVOICE -> InvoiceBuilder(companyService, saleDocumentService, saleDocumentRequest)
        }
    }
}