package cf.jrozen.po.warehouse.domain

import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass()
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class SaleDocument(
        @Id
        val saleDocumentId: String,
        @Column(nullable = false)
        val creationDate: LocalDateTime = LocalDateTime.now(),
        @Column(nullable = false)
        var paymentDate: LocalDateTime,
        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "ORDER")
        val order: Order
)

//class Receipt() : SaleDocument()
//
//class Invoice : SaleDocument()