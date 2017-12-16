package cf.jrozen.po.warehouse.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Customer(
        @Id
        val customerId: String,
        @Column(nullable = false)
        val name: String,
        @Column(nullable = false)
        val creationDate: LocalDateTime = LocalDateTime.now(),
        @OneToMany
        val orders: MutableList<Order> = ArrayList()
) {

    fun canBeDeleted(): Boolean {
        return orders.isEmpty()
    }

}