package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.common.ErrorKeys.NULL_SALE_DOCUMENT_TYPE
import cf.jrozen.po.warehouse.common.RequestValidationException
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

    /**
     * Checks the type of selected sales document in [saleDocumentRequest] and then builds a invoice or receipt.
     * @return invoice or receipt.
     */
    fun getBuilder(saleDocumentRequest: SaleDocumentRequest): AbstractSaleDocumentBuilder {
        return when (saleDocumentRequest.type) {
            SaleDocumentType.RECEIPT -> ReceiptBuilder(companyService, saleDocumentRequest)
            SaleDocumentType.INVOICE -> InvoiceBuilder(companyService, saleDocumentService, saleDocumentRequest)
            null -> throw RequestValidationException("Cannot determine SaleDocumentType", NULL_SALE_DOCUMENT_TYPE)
        }
    }
}