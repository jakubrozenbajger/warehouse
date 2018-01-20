package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.testutils.randomInvoice
import cf.jrozen.po.warehouse.testutils.randomOrderPosition
import cf.jrozen.po.warehouse.testutils.randomReceipt
import org.junit.Assert.*
import org.junit.Test

class ReceiptDocumentPrinterTest{

    @Test
    fun should(){
        val inv = randomReceipt()
        inv.order.orderPosition.add(randomOrderPosition())

        val printer = ReceiptDocumentPrinter(inv, DefaultFinanceProcessingStrategy())
        printer.printDocument()

    }
}