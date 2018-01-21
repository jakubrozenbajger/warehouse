package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.common.CustomerDeletionException
import cf.jrozen.po.warehouse.controller.dto.CustomerDto
import cf.jrozen.po.warehouse.controller.dto.CustomerMapper
import cf.jrozen.po.warehouse.domain.Address
import cf.jrozen.po.warehouse.domain.Customer
import cf.jrozen.po.warehouse.domain.Dealer
import cf.jrozen.po.warehouse.repository.AbstractDatabaseTest
import cf.jrozen.po.warehouse.repository.CustomerRepository
import cf.jrozen.po.warehouse.repository.OrderRepository
import cf.jrozen.po.warehouse.repository.SaleDocumentRepository
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
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

class CustomerServiceTest : AbstractDatabaseTest() {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var orderRepository: OrderRepository

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

    @Test
    fun canDeleteTheSameTwice() {

        customerRepository.save(customer)
        customerService.deleteCustomer(id)

        assertFalse(customerRepository.exists(customer.customerUuid))

        try {
            customerService.deleteCustomer(id)
        } catch (e: Exception) {
            assert(e.cause is EntityNotFoundException)
        }

    }

    @Test
    fun cantDeleteCustomerWithCommitment() {
        val custom = randomCustomer()
        val order = cf.jrozen.po.warehouse.domain.Order(
                "id",
                randomDealer(),
                "descript",
                custom
        )

        customerRepository.save(custom)
        orderRepository.save(order)


        try {
            customerService.deleteCustomer(custom.customerUuid)
        } catch (e: Exception) {
            assert(e.cause is CustomerDeletionException)
        }

    }

}