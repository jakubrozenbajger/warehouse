package cf.jrozen.po.warehouse.testutils

import cf.jrozen.po.warehouse.domain.Address
import cf.jrozen.po.warehouse.domain.Customer
import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.utils.randomUUID
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import java.time.LocalDateTime

object EntityUtils {
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
}

