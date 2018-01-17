package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.common.ErrorKeys.WRONG_PAYMENT_DATE
import cf.jrozen.po.warehouse.domain.SaleDocumentType
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
            if (isDateInvalid(saleDocumentReq))
                errors?.reject(WRONG_PAYMENT_DATE)

        }
    }

    private fun isDateInvalid(sdr: SaleDocumentRequest): Boolean =
            sdr.creationDate?.isBefore(sdr.paymentDate) ?:
                    !sdr.paymentDate.isAfter(LocalDateTime.now())

}