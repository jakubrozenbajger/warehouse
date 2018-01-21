package cf.jrozen.po.warehouse.repository

import cf.jrozen.po.warehouse.controller.dto.CustomerDto
import cf.jrozen.po.warehouse.domain.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * [AddressRepository] stores customer addresses
 */
@Repository
interface AddressRepository : JpaRepository<Address, String>{
}