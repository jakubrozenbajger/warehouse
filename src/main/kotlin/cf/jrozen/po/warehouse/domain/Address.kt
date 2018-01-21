package cf.jrozen.po.warehouse.domain

import cf.jrozen.po.warehouse.utils.randomUUID
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

/**
 * Address information
 */
@Entity
class Address(
        @Id
        @Column(name = "address_uuid")
        val addressUuid: String = randomUUID(),

        @Column(name = "country")
        var country: String,

        @Column(name = "city")
        var city: String,

        @Column(name = "zip_code")
        var zipCode: String,

        @Column(name = "street")
        var street: String,

        @Column(name = "local_number")
        var localNumber: String

) {
    /**
     * It prints the address data as a string
     * @return address in the form of a string
     */
    fun asString(): String {
        val sj = StringJoiner(", ")
        sj.add("$street $localNumber")
        sj.add("$city $zipCode")
        sj.add(country)
        return sj.toString()
    }
}