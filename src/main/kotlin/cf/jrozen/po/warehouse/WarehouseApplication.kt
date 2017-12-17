package cf.jrozen.po.warehouse

import cf.jrozen.po.warehouse.domain.Address
import cf.jrozen.po.warehouse.repository.AddressRepository

import cf.jrozen.po.warehouse.utils.randomUUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class WarehouseApplication

fun main(args: Array<String>) {
    SpringApplication.run(WarehouseApplication::class.java, *args)
    mockDatabase()
}
private fun mockDatabase() {
//    println("###################################################################### mocking db")
//    val addresses = listOf<Address>(Address(randomUUID(), "pl", "asfads", "asdfsa", "asdfdsa"))
//    repo.save(addresses[0])

}
