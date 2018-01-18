package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.domain.SaleDocument
import org.springframework.stereotype.Service
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

    /**
     * Finds the next serial number for the new order.
     * @return the serial number for the new order.
     */
    @Transactional(readOnly = true)
    fun nextSerialNumber(): String {
        return saleDocumentRepository.count().inc().toString()
    }

    /**
     * Creates a new sales document for the [order].
     * Additional information on the type of sales document are included in [saleDocumentRequest].
     * @return the sales document for the given order.
     */
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