package cf.jrozen.po.warehouse.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Customer(
        @Id
        @Column(name = "CUSTOMER_ID")
        val customerId: String,
        @Column(name = "NAME")
        val name: String,
        @Column(name = "CREATION_DATE")
        val creationDate: LocalDateTime,
        @OneToMany
        val orders: MutableList<Order> = ArrayList()
) {

    fun canBeDeleted(): Boolean {
        return orders.isEmpty()
    }

}