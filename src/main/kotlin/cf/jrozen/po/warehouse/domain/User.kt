package cf.jrozen.po.warehouse.domain

import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@Inheritance
abstract class User(

        @Id
        @Column(name = "email")
        val email: String,

        @Column(nullable = false)
        var name: String,

        @Column(nullable = false)
        var surname: String,

        @Column(nullable = false)
        val creationDate: LocalDateTime = LocalDateTime.now(),

        @ManyToMany(mappedBy = "supervisorDealers")
        var supervisingSaleDocuments: MutableSet<SaleDocument> = HashSet()


)


@Entity
@Table(name = "dealers")
class Dealer(
        email: String, name: String, surname: String, creationDate: LocalDateTime = LocalDateTime.now(),
        @OneToMany(mappedBy = "dealer")
        val acceptedOrders: MutableList<Order> = ArrayList()
) : User(email, name, surname, creationDate)
