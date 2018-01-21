package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.SaleDocument
import cf.jrozen.po.warehouse.repository.DocumentRepository
import org.springframework.stereotype.Service
import java.io.OutputStream

/**
 * [DocumentArchiveService] provides CRUD operations on clients.
 * @property documentRepository allows to use the collection of sales documents
 * @property documentPrinterFactory allows to print sales documents
 */
@Service
class DocumentArchiveService(
        val documentRepository: DocumentRepository,
        val documentPrinterFactory: DocumentPrinterFactory
) {
    /**
     * Archives the issued [saleDocument] so that it can not be issued once again.
     */
    fun archive(saleDocument: SaleDocument) {
        val printer = documentPrinterFactory.getPrinter(saleDocument)
        val printedStream: OutputStream = printer.printDocument()
        documentRepository.saveDocument(saleDocument.saleDocumentId, printedStream)
    }
}