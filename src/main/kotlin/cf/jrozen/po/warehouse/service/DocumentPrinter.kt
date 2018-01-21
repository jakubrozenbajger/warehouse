package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Receipt
import java.io.ByteArrayOutputStream
import java.io.OutputStream

interface DocumentPrinter {
    fun printDocument(): OutputStream
}



abstract class AbstractDocumentPrinter(protected val financeProcessingStrategy: FinanceProcessingStrategy) : DocumentPrinter


/**
 * [ReceiptDocumentPrinter] allows to print a receipt
 * @property receipt evidence of sale to be printed
 * @property financeProcessingStrategy calculates the prices of goods depending on taxes
 */
class ReceiptDocumentPrinter(
        val receipt: Receipt,
        financeProcessingStrategy: FinanceProcessingStrategy
) : AbstractDocumentPrinter(financeProcessingStrategy) {

    /**
     * Allows to print a receipt by returning the stream
     * @return stream of bytes to print the receipt
     */
    override fun printDocument(): OutputStream {
        val baos = ByteArrayOutputStream()
        return baos
    }
}