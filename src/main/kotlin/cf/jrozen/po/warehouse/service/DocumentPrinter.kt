package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Receipt
import java.io.ByteArrayOutputStream
import java.io.OutputStream

interface DocumentPrinter {
    fun printDocument(): OutputStream
}



abstract class AbstractDocumentPrinter(protected val financeProcessingStrategy: FinanceProcessingStrategy) : DocumentPrinter



class ReceiptDocumentPrinter(
        val receipt: Receipt,
        financeProcessingStrategy: FinanceProcessingStrategy
) : AbstractDocumentPrinter(financeProcessingStrategy) {

    override fun printDocument(): OutputStream {
        val baos = ByteArrayOutputStream()
        return baos
    }
}