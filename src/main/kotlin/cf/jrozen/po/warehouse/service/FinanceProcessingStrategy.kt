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
    fun calculateGrossPrice(o: Order): BigDecimal
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

    /**
     * Calculates the gross price of the order position [op].
     * @return the gross price of the order position.
     */
    override fun calculateGrossPrice(op: OrderPosition): BigDecimal {
        return calculateNetPrice(op) + calculateVatPrice(op)
    }


    override fun calculateGrossPrice(o: Order): BigDecimal {
        return o.orderPosition.fold(BigDecimal.ZERO) {
            acc, op -> acc + calculateNetPrice(op) + calculateVatPrice(op)
        }
    }

    /**
     * Calculates the corresponding VAT of the order position [op].
     * @return the price of VAT.
     */
    override fun calculateVatPrice(op: OrderPosition): BigDecimal {
        return calculateNetPrice(op) * op.ware.taxGroup.taxAmount
    }

    /**
     * Calculates the net price of the order position [op] multiplied by the number of its occurrence in the order.
     * @return  the total net price of the order position.
     */
    override fun calculateNetPrice(op: OrderPosition): BigDecimal {
        return op.ware.price.value * op.amount
    }

    /**
     * Calculates the net price, VAT and gross price of the order position [op].
     * @return the list with the calculated prices of the order position, the first net price, the second VAT
     * and the last gross price.
     */
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