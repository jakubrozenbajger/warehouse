package cf.jrozen.po.warehouse.domain

import cf.jrozen.po.warehouse.utils.JsonLocalDateTimeDeserializer
import cf.jrozen.po.warehouse.utils.JsonLocalDateTimeSerializer
import cf.jrozen.po.warehouse.utils.randomUUID
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Pattern

@Entity
class Customer(
        @Id
        @Column(name = "customer_uuid")
        val customerUuid: String = randomUUID(),

        @Lob
        @Column(nullable = false)
        var name: String,

        @Lob
        @Column(nullable = true)
        var description: String?,

        @Column(nullable = false)
        @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter::class)
        val creationDate: LocalDateTime = LocalDateTime.now(),

        @Pattern(regexp = "(^$|[0-9]{10})")
        @Column(nullable = true)
        var phoneNumber: String?,

        @Column(name = "nip")
        val nip: String?,

        @OneToOne(targetEntity = Address::class, cascade = [CascadeType.ALL])
        @JoinColumn(name = "address_uuid", referencedColumnName = "address_uuid")
        val address: Address,

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = [CascadeType.ALL])
        @JsonIgnore
        val orders: MutableSet<Order> = HashSet(),

        @OneToMany(fetch = FetchType.LAZY,
                mappedBy = "customer",
                targetEntity = SaleDocument::class,
                cascade = [CascadeType.ALL])
        @JsonIgnore
        val saleDocuments: MutableSet<SaleDocument> = HashSet()
) {

    fun canBeDeleted(): Boolean {
        return orders.isEmpty() && saleDocuments.isEmpty()
    }

}