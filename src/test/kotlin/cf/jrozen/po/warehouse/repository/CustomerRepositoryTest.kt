package cf.jrozen.po.warehouse.repository

import cf.jrozen.po.warehouse.testutils.randomCustomer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class CustomerRepositoryTest : AbstractDatabaseTest() {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Test
    fun shouldAddNewCustomer() {
        //given
        val listOf = listOf(randomCustomer(), randomCustomer(), randomCustomer(), randomCustomer())
        //when
        listOf.forEach { customerRepository.save(it) }
        val findAll = customerRepository.findAll()

        //then
        val map = findAll.map { c -> c.customerUuid }
        val allMatch = listOf.map { c -> c.customerUuid }.fold(true) { b, s: String -> b && map.contains(s) }
        assertTrue(allMatch)
        assertEquals(listOf.size.toLong(), customerRepository.count())
    }

}