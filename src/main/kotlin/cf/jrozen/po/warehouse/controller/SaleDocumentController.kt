package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.service.SaleDocumentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


/**
 * [SaleDocumentController] is responsible for managing with [SaleDocument] entity
 */
@RestController
@RequestMapping("/sale-documents")
class SaleDocumentController(
        val saleDocumentService: SaleDocumentService
) {
    @GetMapping("/{uuid}")
    fun getSaleDocument(@PathVariable(value = "uuid") uuid: String): ResponseEntity<ByteArray> {
        val content = ByteArray(255, { i -> i.toByte() })
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_PDF
        println("Download sample .csv request completed")
        return ResponseEntity<ByteArray>(content, headers, HttpStatus.OK)
    }
}