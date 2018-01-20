package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.controller.dto.AddressDto
import cf.jrozen.po.warehouse.controller.dto.CustomerDto
import org.junit.Assert.*
import org.junit.Test
import org.springframework.validation.Errors

class CustomerValidatorTest{

    val validator = CustomerValidator()

    @Test
    fun completeCustomerWillBeValidaterSuccessfuly(){
        val customer = CustomerDto()
        customer.address = AddressDto()
        customer.name = "name"
        customer.email = "mail"
        val error = Errors()

    }
}