package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Invoice
import cf.jrozen.po.warehouse.domain.Receipt
import cf.jrozen.po.warehouse.domain.SaleDocument
import cf.jrozen.po.warehouse.exception.InvalidSaleDocumentException
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class DocumentPrinterFactory(
        @Qualifier("standardPS")
        val financeProcessingStrategy: FinanceProcessingStrategy
)
{

    fun getPrinter(saleDocument: SaleDocument): DocumentPrinter{
        return when (saleDocument) {
            is Invoice -> InvoiceDocumentPrinter(saleDocument, financeProcessingStrategy)
            is Receipt -> ReceiptDocumentPrinter(saleDocument, financeProcessingStrategy)
            else -> throw InvalidSaleDocumentException("No such type: [${saleDocument.javaClass.simpleName}] of SaleDocument")
        }
    }
}