package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.testutils.randomCustomer
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity.ok
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

class CustomerControllerTest : AbstractControllerTest() {

    @Autowired
    lateinit var customerController: CustomerController

    val baseUrl = "/customers"

    @Test
    fun shouldResponseWith200() {
        mockMvc.perform(get(baseUrl))
                .andExpect { ok() }
    }

    @Test
    fun shouldCreateNewUser() {
        mockMvc.perform(post(baseUrl, randomCustomer()))
                .andExpect { ok() }
    }
}