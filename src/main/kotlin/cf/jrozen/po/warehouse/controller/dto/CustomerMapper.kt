package cf.jrozen.po.warehouse.controller.dto

import cf.jrozen.po.warehouse.controller.CustomerController
import cf.jrozen.po.warehouse.domain.Address
import cf.jrozen.po.warehouse.domain.Customer
import cf.jrozen.po.warehouse.utils.randomUUID
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * [CustomerMapper] is responsible for managing with [Customer] entity
 */
@Component
class CustomerMapper {
    /**
     * Updates the [customerDto] to the [customer]
     * @param customerDto the client from whom the values will be collected
     * @param customer to whom we assign the customerDto values
     * @return updated customer
     */
    fun updateEntity(customerDto: CustomerDto, customer: Customer): Customer {
        with(customerDto) {
            name?.let { customer.name = it }
            email?.let { customer.email = it }
            description.let { customer.description = it }
            phoneNumber.let { customer.phoneNumber = it }
            nip.let { customer.nip = it }
            address?.let {
                with(it) {
                    localNumber?.let { customer.address.localNumber = localNumber }
                    street?.let { customer.address.street = street }
                    zipCode?.let { customer.address.zipCode = zipCode }
                    city?.let { customer.address.city = city }
                    country?.let { customer.address.country = country }
                }
            }
        }
        return customer
    }

    /**
     * Creates a new [Customer]  from the [customerDto]
     * @param customerDto the client from whom the values will be collected
     * @return new customer
     */
    fun createEntity(customerDto: CustomerDto): Customer {
        return Customer(
                customerUuid = randomUUID(),
                name = customerDto.name,
                email = customerDto.email,
                description = customerDto.description,
                creationDate = customerDto.creationDate ?: LocalDateTime.now(),
                phoneNumber = customerDto.phoneNumber,
                nip = customerDto.nip,
                address = Address(
                        addressUuid = randomUUID(),
                        country = customerDto.address.country,
                        city = customerDto.address.city,
                        zipCode = customerDto.address.zipCode,
                        street = customerDto.address.street,
                        localNumber = customerDto.address.localNumber
                ),
                orders = HashSet(),
                saleDocuments = HashSet()
        )
    }
}