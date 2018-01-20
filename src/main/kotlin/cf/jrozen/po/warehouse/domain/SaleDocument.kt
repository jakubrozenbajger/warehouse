package cf.jrozen.po.warehouse.domain

import cf.jrozen.po.warehouse.utils.randomUUID
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters
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
        @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter::class)
        val creationDate: LocalDateTime = LocalDateTime.now(),

        @Column(nullable = false)
        @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter::class)
        var paymentDate: LocalDateTime,

        @Lob
        @Column(nullable = false)
        val sellerInfo: String,

        @Lob
        @Column(nullable = false)
        val customerInfo: String,

        @Lob
        @Column(name = "description", nullable = true)
        var description: String?,

        @OneToOne(targetEntity = Address::class)
        @JoinColumn(name = "customer_address", referencedColumnName = "address_uuid")
        val address: Address,

        @OneToOne(cascade = [(CascadeType.ALL)])
        @JoinColumn(name = "order_uuid")
        val order: Order,

        @ManyToOne(cascade = [(CascadeType.ALL)])
        @JoinColumn(name = "customer_uuid", referencedColumnName = "customer_uuid")
        val customer: Customer,

        @ManyToOne(cascade = [(CascadeType.ALL)])
        @JoinColumn(name = "dealer_email", referencedColumnName = "email")
        val seller: Dealer,

        @ManyToMany(cascade = [(CascadeType.ALL)])
        @JoinTable(name = "sale_documents_supervisors",
                joinColumns = [(JoinColumn(name = "sale_document_uuid",
                        referencedColumnName = "sale_document_uuid"))],
                inverseJoinColumns = [(JoinColumn(name = "dealer_email",
                        referencedColumnName = "email"))])
        val supervisorDealers: MutableSet<Dealer> = HashSet(),

        @Enumerated(value = EnumType.STRING)
        var documentState: DocumentState = DocumentState.NEW
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other !is SaleDocument) return false

                if (saleDocumentId != other.saleDocumentId) return false

                return true
        }

        override fun hashCode(): Int {
                return saleDocumentId.hashCode()
        }
}

enum class SaleDocumentType {
    INVOICE, RECEIPT
}

@Entity
@DiscriminatorValue(value = "RECEIPT")
class Receipt(
        saleDocumentId: String = randomUUID(),
        creationDate: LocalDateTime,
        paymentDate: LocalDateTime,
        sellerInfo: String,
        customerInfo: String,
        description: String?,
        address: Address,
        order: Order,
        customer: Customer,
        seller: Dealer,
        supervisorDealers: MutableSet<Dealer> = HashSet()

) : SaleDocument(
        saleDocumentId,
        creationDate,
        paymentDate,
        sellerInfo,
        customerInfo,
        description,
        address,
        order,
        customer,
        seller,
        supervisorDealers) {
}

@Entity
@DiscriminatorValue(value = "INVOICE")
class Invoice(
        saleDocumentId: String = randomUUID(),
        creationDate: LocalDateTime,
        paymentDate: LocalDateTime,
        sellerInfo: String,
        customerInfo: String,
        description: String?,
        address: Address,
        order: Order,
        customer: Customer,
        seller: Dealer,
        supervisorDealers: MutableSet<Dealer> = HashSet(),
        @Column(name = "serial_number")
        val serialNumber: String

) : SaleDocument(
        saleDocumentId,
        creationDate,
        paymentDate,
        sellerInfo,
        customerInfo,
        description,
        address,
        order,
        customer,
        seller,
        supervisorDealers)