package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.domain.Customer
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class CustomerValidator : Validator {
    override fun validate(target: Any?, errors: Errors?) {
        if (target != null && target is Customer) {
        }
    }

    override fun supports(clazz: Class<*>?): Boolean {
        return clazz?.isAssignableFrom(Customer::class.java) ?: false
    }
}