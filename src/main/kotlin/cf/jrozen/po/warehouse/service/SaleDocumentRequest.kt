package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.SaleDocumentType
import java.time.LocalDateTime

class SaleDocumentRequest(
        val type: SaleDocumentType,
        val paymentDate: LocalDateTime,
        val creationDate: LocalDateTime?
)