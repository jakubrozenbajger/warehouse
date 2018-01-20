package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.common.CustomerDeletionException
import cf.jrozen.po.warehouse.controller.dto.CustomerDto
import cf.jrozen.po.warehouse.controller.dto.CustomerMapper
import cf.jrozen.po.warehouse.domain.Customer
import cf.jrozen.po.warehouse.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class CustomerService(
        val customerRepository: CustomerRepository,
        val customerMapper: CustomerMapper
) {

    /**
     * Adds a new [customer], or updates its data if it already exists.
     * @return the given [customer].
     */
    fun saveCustomer(customerDto: CustomerDto): Customer {
        if (customerRepository.exists(customerDto.customerUuid))
            throw IllegalStateException()
        val newCustomer = customerMapper.createEntity(customerDto)
        return customerRepository.save(newCustomer)
    }

    fun updateCustomer(customerDto: CustomerDto): Customer {
        val customer = customerRepository.getOne(customerDto.customerUuid)
        val updatedCustomer = customerMapper.updateEntity(customerDto, customer)
        return customerRepository.save(updatedCustomer)
    }

    /**
     * Finds the customer with the id number [customerId].
     * @return the customer with the given [customerId].
     */
    @Transactional(readOnly = true)
    fun getCustomer(customerId: String): Customer =
            customerRepository.getOne(customerId)

    /**
     * Deletes the customer with the id number [customerId], if the customer can not be deleted, an exception will be called.
     * @return the deleted customer.
     */
    fun deleteCustomer(customerId: String): Customer {
        val customer = getCustomer(customerId)
        if (customer.canBeDeleted())
            customerRepository.delete(customerId)
        else throw CustomerDeletionException("Cannot delete $customer")
        return customer
    }

    /**
     * Returns a list of warehouse customers.
     * @return list of all warehouse customers.
     */
    fun getAllCustomers(): List<Customer> = customerRepository.findAll()

}