package cf.jrozen.po.warehouse

import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.domain.OrderPosition
import cf.jrozen.po.warehouse.repository.CustomerRepository
import cf.jrozen.po.warehouse.repository.OrderRepository
import cf.jrozen.po.warehouse.repository.SaleDocumentRepository
import cf.jrozen.po.warehouse.repository.UserRepository
import org.apache.commons.lang3.RandomUtils
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashSet

@Component
class DatabaseMocker(
        val customerRepository: CustomerRepository,
        val orderRepository: OrderRepository,
        val userRepository: UserRepository,
        val saleDocumentRepository: SaleDocumentRepository
) : InitializingBean {


    override fun afterPropertiesSet() {
        println("Mocking DB")
        for (i in 0..50) {
            printStatus(i)
            val customer = randomCustomer()
            customerRepository.save(customer)
            val dealer = randomDealer()
            userRepository.save(dealer)
            val set = HashSet<OrderPosition>()
            for (j in 0..RandomUtils.nextInt(2, 20)) {
                set.add(randomOrderPosition())
//                orderRepository
            }
            orderRepository.save(randomOrder(dealer, customer, set))
//            saleDocumentRepository.save(randomInvoice())
        }
    }

    fun printStatus(i: Int) {
        print("$i ")
        if (i%30==0)
            println()
    }
}
