package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.domain.DocumentState
import cf.jrozen.po.warehouse.domain.SaleDocumentType
import cf.jrozen.po.warehouse.repository.OrderRepository
import cf.jrozen.po.warehouse.service.SaleDocumentRequest
import cf.jrozen.po.warehouse.testutils.randomCustomer
import cf.jrozen.po.warehouse.testutils.randomOrder
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDateTime

class OrderControllerTest : AbstractControllerTest() {
    val baseUrl = "/orders"

    @Autowired
    private lateinit var ordersRepository: OrderRepository

    @Test
    fun shouldResponseWith200() {
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl))
                .andExpect { ResponseEntity.ok() }
    }

    @Test
    fun shouldReturnAllOrders() {
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl))
                .andExpect { MockMvcResultMatchers.jsonPath("$").isArray }
    }

    @Test
    fun shouldGenerateInvoice() {
        val sdr = SaleDocumentRequest()
        val sdrRq = ObjectMapper().writeValueAsString(sdr)

        val ord = randomOrder()

        ordersRepository.save(ord)

        sdr.creationDate = LocalDateTime.now().plusDays(1)
        sdr.paymentDate = LocalDateTime.now().plusDays(123)
        sdr.description = "SAMPLE DESC"
        sdr.type = SaleDocumentType.INVOICE
        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + ord.uuid + "sale-document", sdr)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(sdrRq))
                .andExpect { ResponseEntity.ok() }
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value(ord.uuid))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.customerUuid").value(ord.customer.customerUuid))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentState").value(DocumentState.REALIZED.name))
    }
}