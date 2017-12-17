package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Dealer
import cf.jrozen.po.warehouse.domain.User
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthService(
        val userService: UserService
) {

    fun getCurrentUser(): User {
        return userService.getRandomUser()
    }
}