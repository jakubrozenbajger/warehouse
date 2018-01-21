package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.controller.dto.CustomerMapper
import cf.jrozen.po.warehouse.repository.AbstractDatabaseTest
import cf.jrozen.po.warehouse.repository.CustomerRepository
import cf.jrozen.po.warehouse.testutils.randomCustomer
import cf.jrozen.po.warehouse.testutils.randomOrderPosition
import cf.jrozen.po.warehouse.testutils.randomReceipt
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired

class CustomerServiceTest : AbstractDatabaseTest() {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    lateinit var customerService : CustomerService
    var customer = randomCustomer()
    var id = customer.customerUuid

    @Before
    fun bef(){
        customerService = CustomerService(customerRepository, Mockito.mock(CustomerMapper::class.java))
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

        customer.canBeDeleted()

        if (customer.canBeDeleted()) {
            customerService.deleteCustomer(id)
            customerService.deleteCustomer(id)
        }
    }



}