package cf.jrozen.po.warehouse.domain

import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@Inheritance
abstract class User(
        @Id
        val email: String,
        @Column(nullable = false)
        var name: String,
        @Column(nullable = false)
        var surname: String,
        @Column(nullable = false)
        val creationDate: LocalDateTime = LocalDateTime.now()
)

@Entity
class Dealer(
        email: String, name: String, surname: String, creationDate: LocalDateTime = LocalDateTime.now()
) : User(email, name, surname, creationDate)
