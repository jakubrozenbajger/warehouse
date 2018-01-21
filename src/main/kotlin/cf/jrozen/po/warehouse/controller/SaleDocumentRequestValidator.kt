package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.common.ErrorKeys.NULL_SALE_DOCUMENT_TYPE
import cf.jrozen.po.warehouse.common.ErrorKeys.WRONG_PAYMENT_DATE
import cf.jrozen.po.warehouse.domain.Customer
import cf.jrozen.po.warehouse.service.SaleDocumentRequest
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import java.time.LocalDateTime

/**
 * [SaleDocumentRequestValidator] is responsible for managing with [SaleDocumentRequest] entity
 */
@Component
class SaleDocumentRequestValidator : Validator {

    /**
     * Checks if the class [clazz] can be assigned to a class SaleDocumentRequest
     * @param clazz is a class that will be checked
     * @return the logical value of whether the operation was successful
     */
    override fun supports(clazz: Class<*>?): Boolean {
        return clazz?.isAssignableFrom(SaleDocumentRequest::class.java) ?: false
    }

    /**
     * Validates [SaleDocumentRequest]s data in database
     * @param saleDocumentReq is a sale document request to be checked
     * @param errors exception that will be used when an error occurs
     */
    override fun validate(saleDocumentReq: Any?, errors: Errors?) {
        if (saleDocumentReq != null && saleDocumentReq is SaleDocumentRequest) {
            if (saleDocumentReq.type == null)
                errors?.reject(NULL_SALE_DOCUMENT_TYPE)
            if (saleDocumentReq.paymentDate == null)
                errors?.reject("NULL_PAYMENT_DATE")
            else if (isDateInvalid(saleDocumentReq))
                errors?.reject(WRONG_PAYMENT_DATE)
        }
    }

    /**
     * Checks the correctness of the order date
     * @param sdr is a sale document request to be checked
     * @return boolean value that says if the date is correct
     */
    private fun isDateInvalid(sdr: SaleDocumentRequest): Boolean {
        return sdr.paymentDate.isBefore(LocalDateTime.now()) &&
                sdr.creationDate != null && sdr.creationDate.isAfter(sdr.paymentDate)
    }
}