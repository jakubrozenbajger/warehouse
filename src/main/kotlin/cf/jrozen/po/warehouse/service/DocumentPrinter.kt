package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Invoice
import cf.jrozen.po.warehouse.domain.Receipt
import java.io.ByteArrayOutputStream
import java.io.OutputStream

interface DocumentPrinter {
    fun printDocument(): OutputStream
}

abstract class AbstractDocumentPrinter(val processingStrategy: FinanceProcessingStrategy) : DocumentPrinter

class InvoiceDocumentPrinter(
        val invoice: Invoice,
        financeProcessingStrategy: FinanceProcessingStrategy
) : AbstractDocumentPrinter(financeProcessingStrategy) {

    override fun printDocument(): OutputStream {
        val baos = ByteArrayOutputStream()
        return baos
    }
}

class ReceiptDocumentPrinter(
        val receipt: Receipt,
        financeProcessingStrategy: FinanceProcessingStrategy
) : AbstractDocumentPrinter(financeProcessingStrategy) {

    override fun printDocument(): OutputStream {
        val baos = ByteArrayOutputStream()
        return baos
    }
}