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
        @Qualifier("fsRepo")
        val documentRepository: DocumentRepository,
        val saleDocumentRepository: SaleDocumentRepository,
        val saleDocumentBuilderFactory: SaleDocumentBuilderFactory,
        val authService: AuthService
) {

    fun generateNewSaleDocument(order: Order, saleDocumentRequest: SaleDocumentRequest): SaleDocument {
        val builder: AbstractSaleDocumentBuilder = saleDocumentBuilderFactory.getBuilder(saleDocumentRequest)
        val saleDocument: SaleDocument =
                builder.fromOrder(order)
                        .withCreator(authService.getCurrentUser())
                        .build()
        saleDocumentRepository.save(saleDocument)

        processDocument(saleDocument)

        return saleDocument;
    }

    private fun processDocument(saleDocument: SaleDocument) {


//        documentRepository.saveDocument()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}