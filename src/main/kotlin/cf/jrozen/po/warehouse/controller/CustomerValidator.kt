package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.common.ErrorKeys.NULL_EMAIL
import cf.jrozen.po.warehouse.controller.dto.CustomerDto
import cf.jrozen.po.warehouse.domain.Customer
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class CustomerValidator : Validator {

    override fun validate(c: Any?, errors: Errors?) {
        if (c != null && c is CustomerDto) {
            if (c.name == null)
                errors?.reject("NULL_NAME")
            if (c.address == null)
                errors?.reject("NULL_EMAIL")
            if (c.address == null)
                errors?.reject("NULL_ADDRESS")
        }
    }

    override fun supports(clazz: Class<*>?): Boolean {
        return clazz?.isAssignableFrom(CustomerDto::class.java) ?: false
    }
}