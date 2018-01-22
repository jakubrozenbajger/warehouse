package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.testutils.randomCustomer
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
import org.junit.Ignore
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
@Ignore


class CustomerControllerTest : AbstractControllerTest() {

    val baseUrl = "/customers"

    @Test
    fun shouldResponseWith200() {
        mockMvc.perform(get(baseUrl))
                .andExpect { ok() }
    }

    @Test
    fun shouldCreateNewUser() {
        val cst = randomCustomer()
        val cstStr = ObjectMapper().writeValueAsString(cst)
        mockMvc.perform(post(baseUrl, cst).contentType(MediaType.APPLICATION_JSON_UTF8).content(cstStr))
                .andExpect { ok() }
                .andExpect(jsonPath("$.name").value(cst.name))
                .andExpect(jsonPath("$.email").value(cst.email))
                .andExpect(jsonPath("$.phoneNumber").value(cst.phoneNumber))
    }


    @Test
    fun shouldDeleteUser() {
        val cst = randomCustomer()
        val cstStr = ObjectMapper().writeValueAsString(cst)
        var s = ""
        mockMvc.perform(post(baseUrl, cst).contentType(MediaType.APPLICATION_JSON_UTF8).content(cstStr))
                .andExpect { ok() }
                .andExpect(jsonPath("$.name").value(cst.name))
                .andExpect(jsonPath("$.email").value(cst.email))
                .andExpect(jsonPath("$.phoneNumber").value(cst.phoneNumber))
                .andDo { r -> s = r.response.contentAsString }

        val uuid = JSONObject(s).get("customerUuid")

        mockMvc.perform(delete(baseUrl, uuid))
                .andExpect { ok() }
                .andExpect(jsonPath("$.name").value(cst.name))
                .andExpect(jsonPath("$.email").value(cst.email))
                .andExpect(jsonPath("$.phoneNumber").value(cst.phoneNumber))
                .andExpect(jsonPath("$.uuid").value(uuid))

        mockMvc.perform(delete(baseUrl, uuid))
                .andExpect { notFound() }
    }
}