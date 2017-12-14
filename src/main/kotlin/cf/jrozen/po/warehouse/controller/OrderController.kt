package cf.jrozen.po.warehouse.controller

import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.service.OrderService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller("/orders")
class OrderController(val orderService: OrderService) {

    @GetMapping
    fun getOrders(): MutableList<Order> = orderService.getAllOrders()

    @GetMapping("/{orderId}")
    fun getOrder(orderId: String): Order =
            orderService.getOrder(orderId)

    @PutMapping("/{orderId}")
    fun updateOrder(@RequestBody orderId: Order): Order =
            orderService.updateOrder(orderId)

}