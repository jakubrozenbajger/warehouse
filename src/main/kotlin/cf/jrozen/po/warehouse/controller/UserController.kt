package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.service.UserService
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
        val userService: UserService
)