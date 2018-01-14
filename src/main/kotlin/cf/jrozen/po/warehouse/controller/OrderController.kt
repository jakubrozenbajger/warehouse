package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.service.OrderService
import cf.jrozen.po.warehouse.service.SaleDocumentRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
        val orderService: OrderService,
        val saleDocumentRequestValidator: SaleDocumentRequestValidator) {

    @GetMapping
    fun getOrders(): MutableList<Order> =
            orderService.getAllOrders()

    @GetMapping("/{orderId}")
    fun getOrder(@PathVariable("orderId", required = true) orderId: String): Order =
            orderService.getOrder(orderId)

    @PutMapping("/{orderId}")
    fun updateOrder(@RequestBody orderId: Order): Order =
            orderService.updateOrder(orderId)

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
