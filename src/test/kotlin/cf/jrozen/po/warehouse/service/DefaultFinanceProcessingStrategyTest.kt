package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.testutils.randomOrderPosition
import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal

class DefaultFinanceProcessingStrategyTest {

    private val defaultFinanceProcessingStrategy: DefaultFinanceProcessingStrategy = DefaultFinanceProcessingStrategy()

    @Test
    fun shouldCalculateGrossPrice() {
        val position = randomOrderPosition()

        val value = defaultFinanceProcessingStrategy.calculateGrossPrice(position)
        assertEquals(value, position.ware.price.value * position.amount * (BigDecimal.ONE + position.ware.taxGroup.taxAmount))
    }

    @Test
    fun shouldCalculateNetPrice() {
        val position = randomOrderPosition()

        val value = defaultFinanceProcessingStrategy.calculateNetPrice(position)
        assertEquals(value, position.ware.price.value * position.amount)
    }
}