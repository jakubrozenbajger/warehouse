package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.service.SaleDocumentService
import cf.jrozen.po.warehouse.service.UserService
import org.springframework.web.bind.annotation.RestController

/**
 * [UserController] is responsible for managing with [UserService] entity
 * @property userService enables users service
 */
@RestController
class UserController(
        val userService: UserService
)