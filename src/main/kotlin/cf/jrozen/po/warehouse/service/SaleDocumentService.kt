package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.domain.SaleDocument
import org.springframework.stereotype.Service
import cf.jrozen.po.warehouse.repository.SaleDocumentRepository
import org.springframework.transaction.annotation.Transactional
import java.io.FileInputStream

/**
 * [SaleDocumentService] complements the generated invoice
 * @property documentArchiveService allows archiving a sales document
 * @property saleDocumentRepository allows access to sales documents
 * @property saleDocumentBuilderFactory allows to build an invoice
 * @property authService checks user authorization
 */
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
     * @param [order] which the sales document will be generated
     * @param [saleDocumentRequest] type of sales document
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