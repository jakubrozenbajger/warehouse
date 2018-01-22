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
            randomDescription(),
            randomDate(),
            randomPhoneNumber(),
            randomNip(),
            randomAddress(),
            HashSet(),
            HashSet()
    )
}

fun randomNip(): String? {
    return RandomStringUtils.randomNumeric(10)
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

fun randomInvoice(order: Order = randomOrder()): Invoice {
    return Invoice(
            randomUUID(),
            randomDate(),
            randomDate(),
            randomAlphanumeric(),
            randomAlphanumeric(),
            randomDescription(),
            randomAddress(),
            order,
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
            randomDescription(),
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
            randomDescription(),
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
            randomDescription(),
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


fun randomDescription(): String {
    val stringBuilder = StringBuilder()
    for (i in 1..40) {
        stringBuilder.append(RandomStringUtils.randomAlphabetic(0, RandomUtils.nextInt(0, 12)) + " ")
    }
    return stringBuilder.toString()
}





//
//package cf.jrozen.po.warehouse
//
//import cf.jrozen.po.warehouse.domain.*
//import cf.jrozen.po.warehouse.utils.randomUUID
//import com.github.javafaker.Faker
//import org.apache.commons.lang3.RandomStringUtils
//import org.apache.commons.lang3.RandomUtils
//import org.apache.commons.lang3.RandomUtils.nextInt
//import java.math.BigDecimal
//import java.time.LocalDateTime
//import java.util.function.Supplier
//
//
//val faker = Faker()
//
//fun randomCustomer(): Customer {
//    val name = randomName()
//    val email = randomEmail(name)
//    return Customer(
//            randomUUID(),
//            name,
//            email,
//            randomDescription(),
//            randomDate(),
//            faker.phoneNumber().phoneNumber(),
//            randomNip(),
//            randomAddress(),
//            HashSet(),
//            HashSet()
//    )
//}
//
//fun randomName(): String {
//    return faker.name().fullName()
//}
//
//fun randomNip(): String? {
//    return RandomStringUtils.randomNumeric(10)
//}
//
//fun randomEmail(name: String): String {
//    return name.replace(' ', '-').toLowerCase() + "@" + faker.color().name() + "mail.com"
//}
//
//fun randomAddress(): Address {
//    return Address(
//            randomUUID(),
//            randomAlphanumeric(),
//            randomAlphanumeric(),
//            randomAlphanumeric(),
//            randomAlphanumeric(),
//            randomAlphanumeric()
//    )
//}
//
//fun randomAlphanumeric(limit: Int = 19): String {
//    return RandomStringUtils.randomAlphanumeric(4, limit)
//}
//
//fun randomDate(): LocalDateTime {
//    return LocalDateTime.now().minusSeconds(RandomUtils.nextLong(0, 1000000L))
//}
//
//fun randomDateAfter(creationDate: LocalDateTime): LocalDateTime {
//    return creationDate.plusDays(RandomUtils.nextLong(0, 350L))
//}
//
//fun randomInvoice(order: Order = randomOrder()): Invoice {
//    val creationDate = randomDate()
//    return Invoice(
//            randomUUID(),
//            creationDate,
//            randomDateAfter(creationDate),
//            randomAlphanumeric(),
//            randomAlphanumeric(),
//            randomDescription(),
//            randomAddress(),
//            order,
//            randomCustomer(),
//            randomDealer(),
//            HashSet(),
//            randomAlphanumeric()
//    )
//}
//
//fun randomReceipt(): Receipt {
//    return Receipt(
//            randomUUID(),
//            randomDate(),
//            randomDate(),
//            randomAlphanumeric(),
//            randomAlphanumeric(),
//            randomDescription(),
//            randomAddress(),
//            randomOrder(),
//            randomCustomer(),
//            randomDealer(),
//            HashSet()
//    )
//}
//
//fun randomDealer(): Dealer {
//    val name = randomAlphanumeric()
//    val email = randomEmail(name)
//    return Dealer(
//            email,
//            name,
//            randomAlphanumeric(),
//            randomDate(),
//            ArrayList()
//    )
//}
//
//fun randomOrder(dealer: Dealer = randomDealer(),
//                customer: Customer = randomCustomer(),
//                orderPositions: MutableSet<OrderPosition> = HashSet()): Order {
//    return Order(
//            randomUUID(),
//            dealer,
//            randomDescription(),
//            customer,
//            randomDate(),
//            orderPositions,
//            DocumentState.values().let { l -> l[(RandomUtils.nextInt(0, l.size))] }
//    )
//}
//
//fun randomOrderPosition(): OrderPosition {
//    return OrderPosition(
//            randomUUID(),
//            randomBigDecimal(1.1),
//            randomWare(),
//            null
//    )
//}
//
//fun randomWare(): Ware {
//    return Ware(
//            randomUUID(),
//            randomWareName(),
//            randomDescription(),
//            randomDimension(),
//            randomDouble(),
//            randomPrice(),
//            randomTaxGroup()
//    )
//}
//
//fun randomWareName(): String {
//    val randomNames = arrayOf(
//            Supplier { faker.color().name() },
//            Supplier { faker.beer().name() },
//            Supplier { faker.food().spice() },
//            Supplier { faker.pokemon().name() })
//
//    return randomNames[RandomUtils.nextInt(0, randomNames.size)].get()
//}
//
//fun randomDescription(): String {
//    return faker.lorem().sentence(40)
//}
//
//fun randomDimension(): Dimension {
//    return Dimension(
//            randomDouble(),
//            randomDouble(),
//            randomDouble()
//    )
//}
//
//fun randomPrice(): Price {
//    return Price(
//            randomBigDecimal(),
//            "PLN"
//    )
//}
//
//val taxGroups = arrayListOf<Supplier<TaxGroup>>(
//        Supplier { TaxGroup("min", BigDecimal(5)) },
//        Supplier { TaxGroup("min", BigDecimal(8)) },
//        Supplier { TaxGroup("max", BigDecimal(23)) })
//
//
//fun randomTaxGroup(): TaxGroup {
//    return taxGroups[nextInt(0, taxGroups.size)].get()
//}
//
//fun randomDouble(min: Double = 0.1, max: Double = 500.0): Double {
//    return RandomUtils.nextDouble(min, max)
//}
//
//fun randomBigDecimal(min: Double = 0.1, max: Double = 500.0): BigDecimal {
//    return BigDecimal(randomDouble(min, max).toString())
//}
//
