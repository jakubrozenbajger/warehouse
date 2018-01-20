package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Dealer
import cf.jrozen.po.warehouse.domain.User
import cf.jrozen.po.warehouse.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
        val userRepository: UserRepository
) {

    fun getRandomUser(): User {
//        return userRepository.findAll().getOrElse(0) {
        return Dealer(
                "aaa@bb.pl",
                "John",
                "Doe",
                LocalDateTime.now().minusDays(100)
        )

    }

}
