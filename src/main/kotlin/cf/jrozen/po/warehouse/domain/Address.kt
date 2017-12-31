package cf.jrozen.po.warehouse.domain

import cf.jrozen.po.warehouse.utils.randomUUID
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Address(
        @Id
        @Column(name = "address_uuid")
        val addressUuid: String = randomUUID(),

        @Column(name = "country")
        val country: String,

        @Column(name = "city")
        val city: String,

        @Column(name = "zip_code")
        val zipCode: String,

        @Column(name = "street")
        val street: String,

        @Column(name = "local_number")
        val localNumber: String

) {
    fun asString(): String {
        val sj = StringJoiner(", ")
        sj.add("$street $localNumber")
        sj.add("$city $zipCode")
        sj.add(country)
        return sj.toString()
    }
}