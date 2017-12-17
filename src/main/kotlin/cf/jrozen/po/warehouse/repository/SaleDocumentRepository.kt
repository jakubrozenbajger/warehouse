package cf.jrozen.po.warehouse.repository

import cf.jrozen.po.warehouse.domain.SaleDocument
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SaleDocumentRepository : JpaRepository<SaleDocument, String>