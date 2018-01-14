package cf.jrozen.po.warehouse.utils

import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric


fun randomUUID(): String {
    return randomAlphanumeric(8)
}

