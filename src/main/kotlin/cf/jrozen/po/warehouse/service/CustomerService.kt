package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Customer
import cf.jrozen.po.warehouse.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(val customerRepository: CustomerRepository) {

    fun saveCustomer(customer: Customer): Customer = customerRepository.save(customer)

    fun getCustomer(customerId: String): Customer = customerRepository.getOne(customerId)

    fun deleteCustomer(customerId: String): Customer {
        val customer = getCustomer(customerId)
        if (customer.canBeDeleted())
            customerRepository.delete(customerId)
        else throw IllegalArgumentException()
        return customer
    }

}