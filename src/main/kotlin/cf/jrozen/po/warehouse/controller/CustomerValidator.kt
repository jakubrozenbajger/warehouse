package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.common.ErrorKeys.NULL_ADDRESS
import cf.jrozen.po.warehouse.common.ErrorKeys.NULL_CITY
import cf.jrozen.po.warehouse.common.ErrorKeys.NULL_COUNTRY
import cf.jrozen.po.warehouse.common.ErrorKeys.NULL_EMAIL
import cf.jrozen.po.warehouse.common.ErrorKeys.NULL_LOCAL_NUMBER
import cf.jrozen.po.warehouse.common.ErrorKeys.NULL_NAME
import cf.jrozen.po.warehouse.common.ErrorKeys.NULL_STREET
import cf.jrozen.po.warehouse.common.ErrorKeys.NULL_ZIP_CODE
import cf.jrozen.po.warehouse.controller.dto.CustomerDto
import cf.jrozen.po.warehouse.domain.Customer
import org.apache.commons.lang3.StringUtils.isEmpty
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

/**
 * [CustomerValidator] is responsible for managing with [Customer] entity
 */
@Component
class CustomerValidator : Validator {

    /**
     * Validates [Customer]s data in database
     * @param c is a customer to be checked
     * @param errors exception that will be used when an error occurs
     */
    override fun validate(c: Any?, errors: Errors?) {
        if (c != null && c is CustomerDto) {
            if (isEmpty(c.name))
                errors?.reject(NULL_NAME)
            if (isEmpty(c.email))
                errors?.reject(NULL_EMAIL)

            if (c.address == null) {
                errors?.reject(NULL_ADDRESS)
            } else {
                if (isEmpty(c.address.city))
                    errors?.reject(NULL_CITY)
                if (isEmpty(c.address.localNumber))
                    errors?.reject(NULL_LOCAL_NUMBER)
                if (isEmpty(c.address.street))
                    errors?.reject(NULL_STREET)
                if (isEmpty(c.address.country))
                    errors?.reject(NULL_COUNTRY)
                if (isEmpty(c.address.zipCode))
                    errors?.reject(NULL_ZIP_CODE)
            }
        }
    }

    /**
     * Checks if the class [clazz] can be assigned to a class CustomerDto
     * @param clazz is a class that will be checked
     * @return the logical value of whether the operation was successful
     */
    override fun supports(clazz: Class<*>?): Boolean {
        return clazz?.isAssignableFrom(CustomerDto::class.java) ?: false
    }
}