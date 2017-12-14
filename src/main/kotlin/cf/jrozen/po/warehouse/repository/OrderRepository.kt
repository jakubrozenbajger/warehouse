package cf.jrozen.po.warehouse.repository

import cf.jrozen.po.warehouse.domain.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, String> {
}