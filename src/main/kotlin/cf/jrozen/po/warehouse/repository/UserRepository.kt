package cf.jrozen.po.warehouse.repository

import cf.jrozen.po.warehouse.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String>