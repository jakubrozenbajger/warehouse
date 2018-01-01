package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.SaleDocument
import cf.jrozen.po.warehouse.repository.DocumentRepository
import org.springframework.stereotype.Service
import java.io.OutputStream

@Service
class DocumentArchiveService(
        val documentRepository: DocumentRepository,
        val documentPrinterFactory: DocumentPrinterFactory
) {
    fun archive(saleDocument: SaleDocument) {
        val printer = documentPrinterFactory.getPrinter(saleDocument)
        val printedStream: OutputStream = printer.printDocument()
        documentRepository.saveDocument(saleDocument.saleDocumentId, printedStream)
    }
}