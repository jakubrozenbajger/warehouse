package cf.jrozen.po.warehouse.domain

import cf.jrozen.po.warehouse.utils.randomUUID
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@DiscriminatorColumn(name = "sale_document_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "sale_documents")
abstract class SaleDocument(

        @Id
        @Column(name = "sale_document_uuid")
        val saleDocumentId: String = randomUUID(),

        @Column(nullable = false)
        val creationDate: LocalDateTime = LocalDateTime.now(),

        @Column(nullable = false)
        var paymentDate: LocalDateTime,

        @Lob
        @Column(nullable = false)
        val sellerInfo: String,

        @Lob
        @Column(nullable = false)
        val customerInfo: String,

        @OneToOne(targetEntity = Address::class)
        @JoinColumn(name = "customer_address", referencedColumnName = "address_uuid")
        val address: Address,

        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "order_uuid")
        val order: Order,

        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "customer_uuid", referencedColumnName = "customer_uuid")
        val customer: Customer,

        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "dealer_email", referencedColumnName = "email")
        val seller: Dealer,

        @ManyToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinTable(name = "sale_documents_supervisors",
                joinColumns = arrayOf(JoinColumn(name = "sale_document_uuid",
                        referencedColumnName = "sale_document_uuid")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "dealer_email",
                        referencedColumnName = "email")))
        val supervisorDealers: MutableList<Dealer> = ArrayList()
)

@Entity
@DiscriminatorValue(value = "Receipt")
class Receipt(
        saleDocumentId: String = randomUUID(),
        creationDate: LocalDateTime,
        paymentDate: LocalDateTime,
        sellerInfo: String,
        customerInfo: String,
        address: Address,
        order: Order,
        customer: Customer,
        seller: Dealer,
        supervisorDealers: MutableList<Dealer> = ArrayList()

) : SaleDocument(
        saleDocumentId,
        creationDate,
        paymentDate,
        sellerInfo,
        customerInfo,
        address,
        order,
        customer,
        seller,
        supervisorDealers) {
}

@Entity
@DiscriminatorValue(value = "Invoice")
class Invoice(
        saleDocumentId: String = randomUUID(),
        creationDate: LocalDateTime,
        paymentDate: LocalDateTime,
        sellerInfo: String,
        customerInfo: String,
        address: Address,
        order: Order,
        customer: Customer,
        seller: Dealer,
        supervisorDealers: MutableList<Dealer> = ArrayList(),
        @Column(name = "serial_number")
        val serialNumber: String

) : SaleDocument(
        saleDocumentId,
        creationDate,
        paymentDate,
        sellerInfo,
        customerInfo,
        address,
        order,
        customer,
        seller,
        supervisorDealers)