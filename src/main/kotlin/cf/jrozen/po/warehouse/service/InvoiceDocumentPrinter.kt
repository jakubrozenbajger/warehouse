package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Invoice
import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.domain.OrderPosition
import com.itextpdf.text.Element
import com.itextpdf.text.Phrase
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.util.concurrent.atomic.AtomicInteger

class InvoiceDocumentPrinter(
        private val invoice: Invoice,
        financeProcessingStrategy: FinanceProcessingStrategy
) : AbstractDocumentPrinter(financeProcessingStrategy) {

    private var lp: AtomicInteger = resetLp()


    override fun printDocument(): OutputStream {
        val baos = ByteArrayOutputStream()


        // order table
        val orderTable = InvoiceDocumentTemplate.orderTable()
        invoice.order.orderPosition.forEach {
            addOrderPositionToTable(it, orderTable, this::extractOrderPositionProperties)
        }

        // vat table
        val vatTable = InvoiceDocumentTemplate.vatTable()
        fillVatInfo(invoice.order, vatTable)


        resetLp()
        return baos
    }

    private fun resetLp(): AtomicInteger {
        val atomicInteger = AtomicInteger(1)
        lp = atomicInteger
        return atomicInteger
    }

    private fun fillVatInfo(order: Order, vatTable: PdfPTable) {
        val cells = financeProcessingStrategy.calculatePerTaxGroup(order)

        cells.entries.forEach {
            vatTable.addCell(it.key.taxAmount.toString())
            it.value.forEach { bd ->
                vatTable.addCell(bd.toString())
            }
        }

        // sum footer
        vatTable.addCell(InvoiceDocumentTemplate.SUM_CELL_NAME)

        val sum = columnSum(cells.values)//.fold(listOf(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO), columnReduceFunction)
        sum.forEach { vatTable.addCell(it.toString()) }

    }


    private fun addOrderPositionToTable(
            op: OrderPosition,
            t: PdfPTable,
            propsExtractor: (OrderPosition) -> List<String>) {
        propsExtractor(op).forEach { t.addCell(it) }
    }

    private fun extractOrderPositionProperties(op: OrderPosition): List<String> {
        return listOf(
                lp.getAndIncrement().toString(),
                op.ware.name,
                op.amount.toString(),
                op.ware.price.toString(),
                op.ware.taxGroup.taxAmount.toString(),
                financeProcessingStrategy.calculateNetPrice(op).toString(),
                financeProcessingStrategy.calculateVatPrice(op).toString(),
                financeProcessingStrategy.calculateGrossPrice(op).toString()
        )
    }
}

object InvoiceDocumentTemplate {

    val SUM_CELL_NAME = "Suma:"

    private val orderTableHeadersPl = listOf<String>(
            "Lp.",
            "Nazwa towaru",
            "Jm",
            "Ilość",
            "Cena netto",
            "Stawka VAT",
            "Wartość netto",
            "Wartość VAT",
            "Wartość Brutto")

    private val vatTableHeadersPl = listOf<String>(
            "Stawka",
            "Warość netto",
            "Wartość VAT",
            "Wartość brutto")

    private val vatTableLeftColumnPl = listOf<String>(
            "Stawka",
            "Warość netto",
            "Wartość VAT",
            "Wartość brutto")


    /**
     * Passes the order headers to the method that creates the tables in the pdf document.
     * @return calling a function that creates tables with the headers passed in the argument.
     */
    fun orderTable(): PdfPTable {
        return createPdfTable(orderTableHeadersPl)
    }

    /**
     * Passes price type headers to the method that creates the tables in the pdf document.
     * @return calling a function that creates tables with the headers passed in the argument.
     */
    fun vatTable(): PdfPTable {
        return createPdfTable(vatTableHeadersPl)
    }

    private fun createPdfTable(headers: List<String>): PdfPTable {
        val pdfPTable = PdfPTable(headers.size)
        val cells = headers.map { h -> PdfPCell(Phrase(h)) }

        cells.forEach { it.horizontalAlignment = Element.ALIGN_CENTER }
        cells[1].horizontalAlignment = Element.ALIGN_RIGHT
        pdfPTable.headerRows = 1
        return pdfPTable
    }

}