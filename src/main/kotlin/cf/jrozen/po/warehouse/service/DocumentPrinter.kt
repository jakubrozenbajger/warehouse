package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Invoice
import cf.jrozen.po.warehouse.domain.Receipt

interface DocumentPrinter {
    fun printDocument()
}

abstract class AbstractDocumentPrinter(val processingStrategy: FinanceProcessingStrategy) : DocumentPrinter

class InvoiceDocumentPrinter(
        val invoice: Invoice,
        financeProcessingStrategy: FinanceProcessingStrategy
) : AbstractDocumentPrinter(financeProcessingStrategy) {

    override fun printDocument() {
    }
}

class ReceiptDocumentPrinter(
        val receipt: Receipt,
        financeProcessingStrategy: FinanceProcessingStrategy
) : AbstractDocumentPrinter(financeProcessingStrategy) {

    override fun printDocument() {
    }
}