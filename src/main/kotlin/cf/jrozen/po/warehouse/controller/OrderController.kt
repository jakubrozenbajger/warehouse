package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.domain.Customer
import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.service.OrderService
import cf.jrozen.po.warehouse.service.SaleDocumentRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*

/**
 * [OrderController] is responsible for managing with [Order] entity
 */
@RestController
@RequestMapping("/orders")
class OrderController(
        val orderService: OrderService,
        val saleDocumentRequestValidator: SaleDocumentRequestValidator) {

    /**
     * Goes to database for all [Order]s in database
     * @return all [Order]s in repository in JSON format
     */
    @GetMapping
    fun getOrders(): MutableList<Order> =
            orderService.getAllOrders()

    /**
     * Goes to database for [Order] with a specific identifier [orderId] in database
     * @param orderId is specific identifier
     * @return [Order] in repository in JSON format
     */
    @GetMapping("/{orderId}")
    fun getOrder(@PathVariable("orderId", required = true) orderId: String): Order =
            orderService.getOrder(orderId)

    /**
     * Goes to database for specific [Order] in database
     * @param orderId where the change is made
     * @return upgraded [Order] in JSON format
     */
    @PutMapping("/{orderId}")
    fun updateOrder(@RequestBody orderId: Order): Order =
            orderService.updateOrder(orderId)

    /**
     * Goes to the database to generate a sales document with given [orderId]
     * @param orderId of the document that will be generated
     * @param saleDocumentRequest determines the type of sales document
     * @return generated sales document
     */
    @PostMapping("/{orderId}/sale-document")
    fun generateSaleDocument(
            @PathVariable("orderId", required = true) orderId: String,
            @RequestBody @Validated saleDocumentRequest: SaleDocumentRequest): Order {
        return orderService.createSaleDocument(orderId, saleDocumentRequest)
    }

    @InitBinder("saleDocumentRequest")
    fun addCustomerValidator(binder: WebDataBinder) {
        binder.validator = saleDocumentRequestValidator
    }
}
