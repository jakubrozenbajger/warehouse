package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Dealer
import cf.jrozen.po.warehouse.domain.User
import org.springframework.stereotype.Service

@Service
class AuthService(
        val userService: UserService
) {

    fun getCurrentUser(): User {
        return userService.getRandomUser()
    }

    fun getCurrentDealer(): Dealer {
        return getCurrentUser() as? Dealer
                ?: throw NoSuchPrincipalException("Current user is not a Dealer")
    }
}

class NoSuchPrincipalException(msg: String) : IllegalStateException(msg)