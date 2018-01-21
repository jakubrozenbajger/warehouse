package cf.jrozen.po.warehouse.controller

import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
abstract class AbstractControllerTest {

    @Autowired
    private lateinit var wap: WebApplicationContext

    protected lateinit var mockMvc: MockMvc

    @Before
    fun initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wap).build()
    }

}