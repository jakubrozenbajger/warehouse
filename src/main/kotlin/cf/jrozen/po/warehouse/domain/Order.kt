package cf.jrozen.po.warehouse.domain

import cf.jrozen.po.warehouse.utils.randomUUID
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Entity
@Table(name = "orders")
class Order(
        @Id
        @Column(name = "order_uuid")
        val uuid: String = randomUUID(),

        @ManyToOne(targetEntity = Dealer::class)
        @JoinColumn(nullable = false, name = "email", referencedColumnName = "email")
        val dealer: Dealer,

        @ManyToOne(targetEntity = Customer::class)
        @JoinColumn(nullable = false, name = "customer_uuid", referencedColumnName = "customer_uuid")
        val customer: Customer,

        @Column(nullable = false)
        val creationDate: LocalDateTime = LocalDateTime.now(),

        @OneToMany
        @JoinColumn
        var orderPosition: MutableSet<OrderPosition> = HashSet(),

        @Enumerated(value = EnumType.STRING)
        var documentState: DocumentState = DocumentState.NEW

) {
    fun canGenerateSaleDocument(): Boolean {
        return DocumentState.CLOSED != this.documentState
    }
}

@Entity
@Table(name = "order_positions")
class OrderPosition(
        @Id
        @Column(name = "order_position_uuid")
        val uuid: String = randomUUID(),

        @Column(name = "amount")
        var amount: BigDecimal = BigDecimal.ONE,

        @OneToOne()
        @JoinColumn(name = "ware_uuid", nullable = false)
        var ware: Ware,

        @OneToOne(fetch = FetchType.LAZY, optional = true, mappedBy = "order")
        var rabat: Rabat?
)

@Entity
@Table(name = "rabats")
class Rabat(
        @Id
        @Column(name = "rabat_uuid")
        val uuid: String = randomUUID(),

        @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "order_position_uuid")
        val order: OrderPosition,

        @Column(name = "monetary_value", nullable = true)
        val monetaryValue: BigDecimal?,

        @Column(name = "percentage_value", nullable = true)
        val percentageValue: BigDecimal?
)

@Entity
@Table(name = "wares")
class Ware(
        @Id
        @Column(name = "ware_uuid")
        val uuid: String = randomUUID(),

        @Column(name = "name")
        val name: String,

        @Column(name = "description")
        var description: String,

        @Embedded
        val dimensions: Dimension,

        @Column(name = "weight")
        val weight: Double,

        @Embedded
        val price: Price,

        @ManyToOne(targetEntity = TaxGroup::class)
        @JoinColumn(name = "tax_group", referencedColumnName = "name")
        val taxGroup: TaxGroup

)

@Entity
@Table(name = "tax_groups")
class TaxGroup(
        @Id
        @Column(name = "name")
        val name: String,

        @Column(name = "tax_amount")
        val taxAmount: BigDecimal
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TaxGroup) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}


@Embeddable
class Dimension(
        val height: Double?,
        val width: Double?,
        val depth: Double?
)

@Embeddable
class Price(
        val value: BigDecimal,
        val currency: String
)

