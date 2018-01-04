package cf.jrozen.po.warehouse.repository

import cf.jrozen.po.warehouse.domain.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, String>{
}