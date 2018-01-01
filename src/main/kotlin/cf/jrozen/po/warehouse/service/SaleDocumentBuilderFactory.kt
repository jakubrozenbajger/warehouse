package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.SaleDocumentType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class SaleDocumentBuilderFactory(
        val companyService: CompanyService
) {
    @Autowired
    @Lazy
    lateinit var saleDocumentService: SaleDocumentService

    fun getBuilder(saleDocumentRequest: SaleDocumentRequest): AbstractSaleDocumentBuilder {
        return when (saleDocumentRequest.type) {
            SaleDocumentType.RECEIPT -> ReceiptBuilder(companyService, saleDocumentRequest)
            SaleDocumentType.INVOICE -> InvoiceBuilder(companyService, saleDocumentService, saleDocumentRequest)
        }
    }
}