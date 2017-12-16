package cf.jrozen.po.warehouse.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Address(
        @Id
        val addressId: String) {
}