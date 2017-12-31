package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.SaleDocument
import cf.jrozen.po.warehouse.repository.DocumentRepository
import org.springframework.stereotype.Service

@Service
class DocumentArchiveService(
        val documentRepository: DocumentRepository,
        val documentPrinterFactory: DocumentPrinterFactory
) {
    fun archive(saleDocument: SaleDocument) {
        val printer = documentPrinterFactory.getPrinter(saleDocument)

    }
}