package cf.jrozen.po.warehouse.repository

import cf.jrozen.po.warehouse.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * [UserRepository] stores data about users
 */
@Repository
interface UserRepository : JpaRepository<User, String> {}