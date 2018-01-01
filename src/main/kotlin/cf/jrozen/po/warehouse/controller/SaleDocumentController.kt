package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.service.SaleDocumentService
import org.springframework.web.bind.annotation.RestController

@RestController
class SaleDocumentController(
        val saleDocumentService: SaleDocumentService
)