package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.repository.DocumentRepository
import cf.jrozen.po.warehouse.repository.FileSystemDocumentRepository
import cf.jrozen.po.warehouse.service.SaleDocumentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.io.FileInputStream


/**
 * [SaleDocumentController] is responsible for managing with [SaleDocumentService] entity
 * @property saleDocumentService enables sale documents service
 */
@RestController
@RequestMapping("/sale-documents")
class SaleDocumentController(
        val saleDocumentService: SaleDocumentService,
        val documentRepository: DocumentRepository
) {
    /**
     * Gets the sales document witd given [uuid] and prepares it for printing
     * @param [uuid] the number identifying the document that will be printed
     * @return sale document converted to byte arrays
     */
    @GetMapping("/{uuid}")
    fun getSaleDocument(@PathVariable(value = "uuid") uuid: String): ResponseEntity<ByteArray> {
        val saleDocument = documentRepository.getDocument(uuid)
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_PDF
        val filename = "$uuid.pdf"
        headers.setContentDispositionFormData(filename, filename)
        headers.cacheControl = "must-revalidate, post-check=0, pre-check=0"
        println("Sending file")
        return ResponseEntity(saleDocument, headers, HttpStatus.OK)
    }
}