package cf.jrozen.po.warehouse

import cf.jrozen.po.warehouse.common.RestKeys.ENTITY_NOT_FOUND
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.logging.Logger
import javax.persistence.EntityNotFoundException

@ControllerAdvice
@ResponseBody
class ExceptionHandler {

    val logger: Logger = Logger.getLogger(cf.jrozen.po.warehouse.ExceptionHandler::class.simpleName)

    @ExceptionHandler(value = [EntityNotFoundException::class])
    @ResponseStatus(NOT_FOUND)
    fun handleEntityNotFoundException(ex: EntityNotFoundException): String {
        logger.warning(ex.toString())
        return ENTITY_NOT_FOUND
    }
}