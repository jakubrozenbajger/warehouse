package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.common.CustomerDeletionException
import cf.jrozen.po.warehouse.domain.Customer
import cf.jrozen.po.warehouse.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class CustomerService(val customerRepository: CustomerRepository) {

    fun saveCustomer(customer: Customer): Customer =
            customerRepository.save(customer)

    @Transactional(readOnly = true)
    fun getCustomer(customerId: String): Customer =
            customerRepository.getOne(customerId)

    fun deleteCustomer(customerId: String): Customer {
        val customer = getCustomer(customerId)
        if (customer.canBeDeleted())
            customerRepository.delete(customerId)
        else throw CustomerDeletionException("Cannot delete $customer")
        return customer
    }

    fun getAllCustomers(): List<Customer> = customerRepository.findAll()

}