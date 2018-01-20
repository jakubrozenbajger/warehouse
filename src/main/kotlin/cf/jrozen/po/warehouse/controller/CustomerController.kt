package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.controller.dto.CustomerDto
import cf.jrozen.po.warehouse.domain.Customer
import cf.jrozen.po.warehouse.service.CustomerService
import cf.jrozen.po.warehouse.utils.randomUUID
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder

@RestController
@RequestMapping("/customers")
class CustomerController(
        val customerService: CustomerService,
        val customerValidator: CustomerValidator
) {

    @GetMapping
    fun getAllCustomers(): List<Customer> = customerService.getAllCustomers()

    @GetMapping("/{customerUuid}")
    fun getCustomer(@PathVariable customerUuid: String): Customer =
            customerService.getCustomer(customerUuid)

    @PutMapping
    fun updateCustomer(@RequestBody customer: CustomerDto): Customer {
        if (customer.customerUuid == null)
            throw IllegalStateException()
        return customerService.updateCustomer(customer)
    }

    @PostMapping
    fun saveCustomer(@RequestBody @Validated customer: CustomerDto): Customer {
        return customerService.saveCustomer(customer)
    }


    @DeleteMapping("/{customerUuid}")
    fun deleteCustomer(@PathVariable customerUuid: String): Customer =
            customerService.deleteCustomer(customerUuid)

    @InitBinder("customer")
    fun addCustomerValidator(binder: WebDataBinder) {
        binder.validator = customerValidator
    }
}