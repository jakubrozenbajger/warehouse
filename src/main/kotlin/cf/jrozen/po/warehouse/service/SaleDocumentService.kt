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
        val saleDocumentRepository: SaleDocumentRepository
) {

    fun generateNewSaleDocument(order: Order, saleDocumentRequest: SaleDocumentRequest): SaleDocument {
        TODO()
    }

}