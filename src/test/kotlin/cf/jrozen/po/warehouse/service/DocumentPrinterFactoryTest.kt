package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.SaleDocument
import cf.jrozen.po.warehouse.testutils.randomInvoice
import cf.jrozen.po.warehouse.testutils.randomReceipt
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DocumentPrinterFactoryTest {
    var documentPrinterFactory: DocumentPrinterFactory = DocumentPrinterFactory(DefaultFinanceProcessingStrategy())

    @Before
    fun init() {
        documentPrinterFactory = DocumentPrinterFactory(DefaultFinanceProcessingStrategy())
    }

    @Test
    fun shouldReturnProperType() {
        val invoice = randomInvoice()

        val printer = documentPrinterFactory.getPrinter(invoice)
        assertTrue(printer.javaClass.isAssignableFrom(InvoiceDocumentPrinter::class.java))
        assertFalse(printer.javaClass.isAssignableFrom(ReceiptDocumentPrinter::class.java))
    }

    @Test
    fun shouldReturnProperTypeReceipt() {
        val receipt= randomReceipt()

        val printer = documentPrinterFactory.getPrinter(receipt)
        assertFalse(printer.javaClass.isAssignableFrom(InvoiceDocumentPrinter::class.java))
        assertTrue(printer.javaClass.isAssignableFrom(ReceiptDocumentPrinter::class.java))
    }
//
//    @Test(expected = IllegalStateException::class)
//    fun shouldReturnProperTypeReceipt() {
////        val receipt= object : SaleDocument(){}
//
//        val printer = documentPrinterFactory.getPrinter(receipt)
//        assertFalse(printer.javaClass.isAssignableFrom(InvoiceDocumentPrinter::class.java))
//        assertTrue(printer.javaClass.isAssignableFrom(ReceiptDocumentPrinter::class.java))
//    }

}