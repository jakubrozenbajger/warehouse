package cf.jrozen.po.warehouse

import cf.jrozen.po.warehouse.repository.CustomerRepository
import cf.jrozen.po.warehouse.repository.OrderRepository
import cf.jrozen.po.warehouse.repository.SaleDocumentRepository
import cf.jrozen.po.warehouse.repository.UserRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

@Component
class DatabaseMocker(
        val customerRepository: CustomerRepository,
        val orderRepository: OrderRepository,
        val userRepository: UserRepository,
        val saleDocumentRepository: SaleDocumentRepository
) : InitializingBean {


    override fun afterPropertiesSet() {
        for (i in 0..10) {
            val customer = randomCustomer()
            customerRepository.save(customer)
            val dealer = randomDealer()
            userRepository.save(dealer)
            orderRepository.save(randomOrder(dealer, customer))
//            saleDocumentRepository.save(randomInvoice())
        }
    }
}
