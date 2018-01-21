package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.service.SaleDocumentService
import org.springframework.web.bind.annotation.RestController

/**
 * [SaleDocumentController] is responsible for managing with [SaleDocumentService] entity
 */
@RestController
class SaleDocumentController(
        val saleDocumentService: SaleDocumentService
)