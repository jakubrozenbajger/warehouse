package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.User
import org.springframework.stereotype.Service

@Service
class AuthService(
        val userService: UserService
) {

    fun getCurrentUser(): User {
        return userService.getRandomUser()
    }
}