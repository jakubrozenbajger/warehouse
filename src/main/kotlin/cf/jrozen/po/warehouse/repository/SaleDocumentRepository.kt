package cf.jrozen.po.warehouse.repository

import cf.jrozen.po.warehouse.domain.SaleDocument
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * [SaleDocumentRepository] stores data about sale documents
 */
@Repository
interface SaleDocumentRepository : JpaRepository<SaleDocument, String> {

    @Query(
            value = "SELECT sd.sale_document_uuid FROM sale_documents sd WHERE sd.order_uuid = :orderId",
            nativeQuery = true
    )
    fun getOrdersSaleDocumentId(@Param("orderId") orderId: String): String?

}