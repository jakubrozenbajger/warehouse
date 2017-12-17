package cf.jrozen.po.warehouse.repository

import cf.jrozen.po.warehouse.domain.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository : JpaRepository<Address, String>{
}