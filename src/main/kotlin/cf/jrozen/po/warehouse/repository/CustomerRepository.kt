package cf.jrozen.po.warehouse.repository

import cf.jrozen.po.warehouse.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * [CustomerRepository] stores customer data
 */
@Repository
interface CustomerRepository : JpaRepository<Customer, String> {
}