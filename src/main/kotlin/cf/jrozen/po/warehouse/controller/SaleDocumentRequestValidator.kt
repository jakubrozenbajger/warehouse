package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.common.ErrorKeys.WRONG_PAYMENT_DATE
import cf.jrozen.po.warehouse.service.SaleDocumentRequest
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import java.time.LocalDateTime

@Component
class SaleDocumentRequestValidator : Validator {
    override fun validate(target: Any?, errors: Errors?) {
        if (target != null && target is SaleDocumentRequest) {
            if (target.creationDate?.isBefore(target.paymentDate) ?:
                    !target.paymentDate.isAfter(LocalDateTime.now()))
                errors?.reject(WRONG_PAYMENT_DATE)

        }
    }

    override fun supports(clazz: Class<*>?): Boolean {
        return clazz?.isAssignableFrom(SaleDocumentRequest::class.java) ?: false
    }

}