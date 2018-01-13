package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.testutils.randomInvoice
import cf.jrozen.po.warehouse.testutils.randomOrderPosition
import org.junit.Test

class InvoiceDocumentPrinterTest {


    @Test
    fun should(){
        val inv = randomInvoice()
        inv.order.orderPosition.add(randomOrderPosition())
        inv.order.orderPosition.add(randomOrderPosition())
        inv.order.orderPosition.add(randomOrderPosition())




        val printer = InvoiceDocumentPrinter(inv, DefaultFinanceProcessingStrategy())
        printer.printDocument()

    }

}