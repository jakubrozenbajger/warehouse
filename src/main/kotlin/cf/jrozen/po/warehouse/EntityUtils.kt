package cf.jrozen.po.warehouse

import cf.jrozen.po.warehouse.domain.*
import cf.jrozen.po.warehouse.utils.randomUUID
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import java.math.BigDecimal
import java.time.LocalDateTime

fun randomCustomer(): Customer {
    return Customer(
            randomUUID(),
            randomAlphanumeric(),
            randomEmail(),
            randomAlphanumeric(),
            randomDate(),
            randomPhoneNumber(),
            randomAlphanumeric(),
            randomAddress(),
            HashSet(),
            HashSet()
    )
}

fun randomEmail(): String {
    return randomAlphanumeric(5) + "@" + randomAlphanumeric(6) + ".com"
}

fun randomAddress(): Address {
    return Address(
            randomUUID(),
            randomAlphanumeric(),
            randomAlphanumeric(),
            randomAlphanumeric(),
            randomAlphanumeric(),
            randomAlphanumeric()
    )
}

fun randomAlphanumeric(limit: Int = 19): String {
    return RandomStringUtils.randomAlphanumeric(4, limit)
}

fun randomPhoneNumber(): String {
    return RandomStringUtils.randomNumeric(10)
}

fun randomDate(): LocalDateTime {
    return LocalDateTime.now().minusSeconds(RandomUtils.nextLong(0, 1000000L))
}

fun randomInvoice(): Invoice {
    return Invoice(
            randomUUID(),
            randomDate(),
            randomDate(),
            randomAlphanumeric(),
            randomAlphanumeric(),
            randomAlphanumeric(),
            randomAddress(),
            randomOrder(),
            randomCustomer(),
            randomDealer(),
            HashSet(),
            randomAlphanumeric()
    )
}

fun randomReceipt(): Receipt {
    return Receipt(
            randomUUID(),
            randomDate(),
            randomDate(),
            randomAlphanumeric(),
            randomAlphanumeric(),
            randomAlphanumeric(),
            randomAddress(),
            randomOrder(),
            randomCustomer(),
            randomDealer(),
            HashSet()
    )
}

fun randomDealer(): Dealer {
    return Dealer(
            randomAlphanumeric(),
            randomAlphanumeric(),
            randomAlphanumeric(),
            randomDate(),
            ArrayList()
    )
}

fun randomOrder(dealer: Dealer = randomDealer(),
                customer: Customer = randomCustomer(),
                orderPositions: MutableSet<OrderPosition> = HashSet()): Order {
    return Order(
            randomUUID(),
            dealer,
            randomAlphanumeric(),
            customer,
            randomDate(),
            orderPositions,
            DocumentState.values().let { l -> l[(RandomUtils.nextInt(0, l.size))] }
    )
}

fun randomOrderPosition(): OrderPosition {
    return OrderPosition(
            randomUUID(),
            randomBigDecimal(1.1),
            randomWare(),
            null
    )
}

fun randomWare(): Ware {
    return Ware(
            randomUUID(),
            randomAlphanumeric(),
            randomAlphanumeric(),
            randomDimension(),
            randomDouble(),
            randomPrice(),
            randomTaxGroup()
    )

}

fun randomDimension(): Dimension {
    return Dimension(
            randomDouble(),
            randomDouble(),
            randomDouble()
    )
}

fun randomPrice(): Price {
    return Price(
            randomBigDecimal(),
            randomAlphanumeric()
    )
}

fun randomTaxGroup(): TaxGroup {
    return TaxGroup(
            randomAlphanumeric(),
            randomBigDecimal()
    )
}

fun randomDouble(min: Double = 0.1): Double {
    return RandomUtils.nextDouble(min, 500.0)
}

fun randomBigDecimal(min: Double = 0.1): BigDecimal {
    return BigDecimal(randomDouble(min).toString())
}


