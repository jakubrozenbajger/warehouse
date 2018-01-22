package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.controller.dto.AddressDto
import cf.jrozen.po.warehouse.controller.dto.CustomerDto
import org.junit.Assert.*
import org.junit.Test
import org.springframework.validation.BeanPropertyBindingResult

class CustomerValidatorTest {

    val validator = CustomerValidator()

    @Test
    fun completeCustomerWillBeValidaterSuccessfuly() {
        val customer = CustomerDto()
        customer.name = "name"
        customer.email = "mail"
        customer.address = newAddressDto(
                pCity = "city",
                pLocalNumber = "num",
                pStreet = "strit",
                pCountry = "cnt",
                pZipCode = "82-300"
        )
        val error = BeanPropertyBindingResult(customer, "customer")
        validator.validate(customer, error)
        assertEquals(error.errorCount, 0)
    }

    @Test
    fun nonCompleteCustomerShouldFailAtValidate() {
        val customer = CustomerDto()
        customer.email = "mail"
        customer.address = newAddressDto(
                pCity = "city",
                pLocalNumber = "num",
                pStreet = "strit",
                pCountry = "cnt",
                pZipCode = "82-300"
        )
        val error = BeanPropertyBindingResult(customer, "customer")
        validator.validate(customer, error)
        assertEquals(error.errorCount, 1)
    }

    @Test
    fun shouldFailWhenEmptyStings() {
        val customer = CustomerDto()
        customer.email = ""
        customer.address = newAddressDto(
                pCity = "",
                pLocalNumber = "",
                pStreet = "",
                pCountry = "",
                pZipCode = ""
        )
        val error = BeanPropertyBindingResult(customer, "")
        validator.validate(customer, error)
        assertTrue(error.hasErrors())
    }

    private fun newAddressDto(
            pCity: String,
            pLocalNumber: String,
            pStreet: String,
            pCountry: String,
            pZipCode: String
    ): AddressDto {
        val addressDto = AddressDto()
        with(addressDto) {
            city = pCity
            localNumber = pLocalNumber
            street = pStreet
            country = pCountry
            zipCode = pZipCode
        }
        return addressDto
    }
}