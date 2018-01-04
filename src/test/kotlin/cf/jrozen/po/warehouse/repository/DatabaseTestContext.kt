package cf.jrozen.po.warehouse.repository

import org.springframework.context.annotation.ComponentScan

import org.springframework.test.context.ContextConfiguration

@ContextConfiguration
@ComponentScan(value = [
    "cf.jrozen.po.warehouse.repository.*"
])
open class DatabaseTestContext {
}
