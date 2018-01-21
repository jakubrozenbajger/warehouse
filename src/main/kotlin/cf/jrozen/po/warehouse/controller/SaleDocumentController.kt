package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.domain.Customer
import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.service.SaleDocumentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


/**
 * [SaleDocumentController] is responsible for managing with [SaleDocumentService] entity
 * @property saleDocumentService enables sale documents service
 */
@RestController
@RequestMapping("/sale-documents")
class SaleDocumentController(
        val saleDocumentService: SaleDocumentService
) {
    /**
     * Gets the sales document witd given [uuid] and prepares it for printing
     * @param [uuid] the number identifying the document that will be printed
     * @return sale document converted to byte arrays
     */
    @GetMapping("/{uuid}")
    fun getSaleDocument(@PathVariable(value = "uuid") uuid: String): ResponseEntity<ByteArray> {
        val content = ByteArray(255, { i -> i.toByte() })
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_PDF
        println("Download sample .csv request completed")
        return ResponseEntity<ByteArray>(content, headers, HttpStatus.OK)
    }
}