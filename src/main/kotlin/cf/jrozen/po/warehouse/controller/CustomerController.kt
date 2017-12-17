package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.domain.Customer
import cf.jrozen.po.warehouse.service.CustomerService
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

    @GetMapping("/{customerId}")
    fun getCustomer(customerId: String): Customer = customerService.getCustomer(customerId)

    @PutMapping
    fun updateCustomer(@RequestBody @Validated customer: Customer) = customerService.saveCustomer(customer)

    @PostMapping
    fun saveCustomer(@RequestBody @Validated customer: Customer) = customerService.saveCustomer(customer)

    @DeleteMapping("/{customerId}")
    fun deleteCustomer(customerId: String) = customerService.deleteCustomer(customerId)

    @InitBinder("customer")
    fun addCustomerValidator(binder: WebDataBinder) {
        binder.validator = customerValidator
    }
}