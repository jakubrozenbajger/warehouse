package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Invoice
import cf.jrozen.po.warehouse.domain.Receipt
import cf.jrozen.po.warehouse.domain.SaleDocument
import cf.jrozen.po.warehouse.common.InvalidSaleDocumentException
import org.springframework.stereotype.Component

@Component
class DocumentPrinterFactory(
        val financeProcessingStrategy: FinanceProcessingStrategy
)
{
    /**
     * Creates evidence of sale for printing based on [saleDocument] choice, receipt or invoice.
     * @return the type of sales proof prepared for printing.
     */
    fun getPrinter(saleDocument: SaleDocument): DocumentPrinter{
        return when (saleDocument) {
            is Invoice -> InvoiceDocumentPrinter(saleDocument, financeProcessingStrategy)
            is Receipt -> ReceiptDocumentPrinter(saleDocument, financeProcessingStrategy)
            else -> throw InvalidSaleDocumentException("No such type: [${saleDocument.javaClass.simpleName}] of SaleDocument")
        }
    }
}