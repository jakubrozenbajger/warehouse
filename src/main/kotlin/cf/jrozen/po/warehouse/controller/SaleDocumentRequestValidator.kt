package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.common.ErrorKeys.NULL_SALE_DOCUMENT_TYPE
import cf.jrozen.po.warehouse.common.ErrorKeys.WRONG_PAYMENT_DATE
import cf.jrozen.po.warehouse.service.SaleDocumentRequest
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import java.time.LocalDateTime

@Component
class SaleDocumentRequestValidator : Validator {

    override fun supports(clazz: Class<*>?): Boolean {
        return clazz?.isAssignableFrom(SaleDocumentRequest::class.java) ?: false
    }

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

    private fun isDateInvalid(sdr: SaleDocumentRequest): Boolean {
        return sdr.paymentDate.isBefore(LocalDateTime.now()) &&
                sdr.creationDate != null && sdr.creationDate.isAfter(sdr.paymentDate)
    }
}