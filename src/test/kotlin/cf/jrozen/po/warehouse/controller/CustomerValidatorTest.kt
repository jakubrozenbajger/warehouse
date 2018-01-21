package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.controller.dto.AddressDto
import cf.jrozen.po.warehouse.controller.dto.CustomerDto
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.springframework.validation.AbstractErrors
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors

class CustomerValidatorTest{

    val validator = CustomerValidator()

    @Test
    fun completeCustomerWillBeValidaterSuccessfuly(){
        val customer = CustomerDto()
        customer.address = AddressDto()
        customer.name = "name"
        customer.email = "mail"
        customer.address.apply {
            city = "city"
            localNumber = "num"
            street = "strit"
            country = "cnt"
            zipCode = "82-300"
        }
        val error = BeanPropertyBindingResult(customer, "customer" )
        validator.validate(customer, error)
        assert(error.errorCount == 0 )
    }

    @Test
    fun nonCompleteCustomerShouldFailAtValidate(){
        val customer = CustomerDto()
        customer.address = AddressDto()
        customer.email = "mail"
        customer.address.apply {
            city = "city"
            localNumber = "num"
            street = "strit"
            country = "cnt"
            zipCode = "82-300"
        }
        val error = BeanPropertyBindingResult(customer, "customer" )
        validator.validate(customer, error)
        assert(error.errorCount == 1 )
    }
}