package cf.jrozen.po.warehouse.common

class RequestValidationException(msg: String, val restKey: String) : IllegalStateException(msg)
