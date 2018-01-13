package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.domain.Order
import cf.jrozen.po.warehouse.domain.OrderPosition
import cf.jrozen.po.warehouse.domain.TaxGroup
import org.springframework.stereotype.Component
import java.math.BigDecimal

interface FinanceProcessingStrategy {
    fun calculateNetPrice(op: OrderPosition): BigDecimal
    fun calculateVatPrice(op: OrderPosition): BigDecimal
    fun calculateGrossPrice(op: OrderPosition): BigDecimal
    fun calculatePerTaxGroup(order: Order): Map<TaxGroup, List<BigDecimal>>
}

@Component
class DefaultFinanceProcessingStrategy : FinanceProcessingStrategy {

    override fun calculatePerTaxGroup(order: Order): Map<TaxGroup, List<BigDecimal>> {
        val map = order.orderPosition.groupBy { op: OrderPosition -> op.ware.taxGroup }
        return map.mapValues { e ->
            e.value.map {
                toTaxTuple(it)
            }.fold(listOf(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO), columnReduceFunction)
        }
    }

    override fun calculateGrossPrice(op: OrderPosition): BigDecimal {
        return calculateNetPrice(op) + calculateVatPrice(op)
    }

    override fun calculateVatPrice(op: OrderPosition): BigDecimal {
        return calculateNetPrice(op) * op.ware.taxGroup.taxAmount
    }

    override fun calculateNetPrice(op: OrderPosition): BigDecimal {
        return op.ware.price.value * op.amount
    }


    fun toTaxTuple(op: OrderPosition): List<BigDecimal> {
        return listOf(
                calculateNetPrice(op),
                calculateVatPrice(op),
                calculateGrossPrice(op)
        )
    }
}

val columnReduceFunction = { acc: List<BigDecimal>, list: List<BigDecimal> ->
    listOf(
            acc[0] + list[0],
            acc[1] + list[1],
            acc[2] + list[2]
    )
}

fun columnSum(c: Collection<List<BigDecimal>>): List<BigDecimal> {
    return c.fold(listOf(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO), columnReduceFunction)

}