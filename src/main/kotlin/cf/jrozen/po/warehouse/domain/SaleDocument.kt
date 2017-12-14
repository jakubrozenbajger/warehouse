package cf.jrozen.po.warehouse.domain

abstract class SaleDocument {
}

class Receipt : SaleDocument()

class Invoice : SaleDocument()