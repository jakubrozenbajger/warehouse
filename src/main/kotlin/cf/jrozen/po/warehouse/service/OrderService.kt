package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(val orderRepository: OrderRepository) {

    fun getAllOrders(): MutableList<Order> = orderRepository.findAll()

    fun getOrder(orderId: String): Order = orderRepository.getOne(orderId)

    fun updateOrder(order: Order): Order = orderRepository.save(order)


}