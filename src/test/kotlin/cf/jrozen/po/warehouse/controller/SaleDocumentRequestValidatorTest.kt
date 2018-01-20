package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.controller.dto.AddressDto
import cf.jrozen.po.warehouse.controller.dto.CustomerDto
import cf.jrozen.po.warehouse.domain.SaleDocumentType
import cf.jrozen.po.warehouse.service.SaleDocumentRequest
import org.junit.Assert.*
import org.junit.Test
import org.springframework.validation.BeanPropertyBindingResult
import java.time.LocalDateTime

class SaleDocumentRequestValidatorTest{

    val validator = SaleDocumentRequestValidator()

    @Test
    fun completeSaleDocumentRequestWillBeValidaterSuccessfuly(){
        val saledocumentRequest = SaleDocumentRequest()
        saledocumentRequest.type= SaleDocumentType.INVOICE
        saledocumentRequest.paymentDate= LocalDateTime.MAX

        val error = BeanPropertyBindingResult(saledocumentRequest, "saledocumentRequest" )
        validator.validate(saledocumentRequest, error)
        assert(error.errorCount == 0 )
    }

    @Test
    fun noncompleteSaleDocumentRequestShouldFailAtValidate(){
        val saledocumentRequest = SaleDocumentRequest()
        saledocumentRequest.type= SaleDocumentType.INVOICE

        val error = BeanPropertyBindingResult(saledocumentRequest, "saledocumentRequest" )
        validator.validate(saledocumentRequest, error)
        assert(error.errorCount == 1 )
    }

}