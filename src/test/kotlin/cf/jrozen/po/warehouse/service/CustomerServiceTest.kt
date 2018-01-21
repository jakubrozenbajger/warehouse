package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.common.CustomerDeletionException
import cf.jrozen.po.warehouse.controller.dto.CustomerDto
import cf.jrozen.po.warehouse.controller.dto.CustomerMapper
import cf.jrozen.po.warehouse.domain.Address
import cf.jrozen.po.warehouse.domain.Customer
import cf.jrozen.po.warehouse.domain.Dealer
import cf.jrozen.po.warehouse.repository.*
import cf.jrozen.po.warehouse.testutils.randomCustomer
import cf.jrozen.po.warehouse.testutils.randomDealer
import cf.jrozen.po.warehouse.testutils.randomOrderPosition
import cf.jrozen.po.warehouse.testutils.randomReceipt
import org.hibernate.criterion.Order
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

class CustomerServiceTest : AbstractDatabaseTest() {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var userRepository: UserRepository

    lateinit var orderService: OrderService
    lateinit var customerService: CustomerService

    var customer = randomCustomer()
    var id = customer.customerUuid

    @Before
    fun bef() {
        customerService = CustomerService(customerRepository, Mockito.mock(CustomerMapper::class.java))
        orderService = OrderService(orderRepository, Mockito.mock(SaleDocumentService::class.java), Mockito.mock(FinanceProcessingStrategy::class.java), Mockito.mock(SaleDocumentRepository::class.java))
    }

    @Test
    fun canDeleteCustomer() {

        customerRepository.save(customer)
//        var list = customerService.getAllCustomers()
//        assertTrue(list[0].customerUuid == customer.customerUuid)
        if (customer.canBeDeleted()) {
            customerService.deleteCustomer(id)
            var list = customerService.getAllCustomers()
            assertFalse(list.contains(customer))
        }
    }

    @Test(expected = JpaObjectRetrievalFailureException::class)
    fun canDeleteTheSameTwice() {

        customerRepository.save(customer)
        customerService.deleteCustomer(id)

        assertFalse(customerRepository.exists(customer.customerUuid))

        customerService.deleteCustomer(id)
    }

    @Test(expected = CustomerDeletionException::class)
    fun cantDeleteCustomerWithCommitment() {
        //given
        val custom = randomCustomer()
        val dealer = randomDealer()
        val order = cf.jrozen.po.warehouse.domain.Order(
                "id",
                dealer,
                "descript",
                custom
        )
        custom.orders.add(order)
        userRepository.save(dealer)
        customerRepository.save(custom)
        orderRepository.save(order)

        //when
        customerService.deleteCustomer(custom.customerUuid)
    }

}