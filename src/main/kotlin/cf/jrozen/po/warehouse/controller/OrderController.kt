package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.domain.SaleDocumentType
import cf.jrozen.po.warehouse.service.OrderService
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/orders")
class OrderController(val orderService: OrderService) {

    @GetMapping("/")
    fun getOrders(): MutableList<Order> = orderService.getAllOrders()

    @GetMapping("/{orderId}")
    fun getOrder(orderId: String): Order =
            orderService.getOrder(orderId)

    @PutMapping("/{orderId}")
    fun updateOrder(@RequestBody orderId: Order): Order =
            orderService.updateOrder(orderId)

    @PostMapping("/{orderId}/sale-document")
    fun generateSaleDocument(
            @PathVariable("orderId", required = true) orderId: String,
            @RequestBody saleDocumentRequest: SaleDocumentRequest): Order {
        return orderService.createSaleDocument(orderId, saleDocumentRequest)
    }
}

class SaleDocumentRequest(
        val type: SaleDocumentType,
        val paymentDate: LocalDateTime,
        val creationDate: LocalDateTime?
)