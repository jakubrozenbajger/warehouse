package cf.jrozen.po.warehouse.testutils

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
            randomAlphanumeric(),
            randomDate(),
            randomPhoneNumber(),
            randomAlphanumeric(),
            randomAddress(),
            HashSet(),
            HashSet()
    )
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

fun randomAlphanumeric(): String {
    return RandomStringUtils.randomAlphanumeric(8, 19)
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
            randomAddress(),
            randomOrder(),
            randomCustomer(),
            randomDealer(),
            HashSet(),
            randomAlphanumeric()
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

fun randomOrder(): Order {
    return Order(
            randomUUID(),
            randomDealer(),
            randomCustomer(),
            randomDate(),
            HashSet(),
            DocumentState.NEW
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


