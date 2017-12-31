package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.controller.SaleDocumentRequest
import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.domain.SaleDocument
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import cf.jrozen.po.warehouse.repository.DocumentRepository
import cf.jrozen.po.warehouse.repository.SaleDocumentRepository
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class SaleDocumentService(
        val documentArchiveService: DocumentArchiveService,
        val saleDocumentRepository: SaleDocumentRepository,
        val saleDocumentBuilderFactory: SaleDocumentBuilderFactory,
        val authService: AuthService
) {

    @Transactional(readOnly = true)
    fun nextSerialNumber(): String {
        return saleDocumentRepository.count().inc().toString()
    }

    fun generateNewSaleDocument(order: Order, saleDocumentRequest: SaleDocumentRequest): SaleDocument {
        val builder: AbstractSaleDocumentBuilder = saleDocumentBuilderFactory.getBuilder(saleDocumentRequest)
        val saleDocument: SaleDocument =
                builder.fromOrder(order)
                        .withCreator(authService.getCurrentDealer())
                        .build()
        saleDocumentRepository.save(saleDocument)

        documentArchiveService.archive(saleDocument)

        return saleDocument
    }

}