package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.testutils.randomInvoice
import cf.jrozen.po.warehouse.testutils.randomOrderPosition
import org.junit.Assert.*
import org.junit.Test

class DefaultFinanceProcessingStrategyTest{

    val defaultFinanceProcessingStrategy: DefaultFinanceProcessingStrategy = DefaultFinanceProcessingStrategy()

    @Test
    fun should(){
        val inv = randomOrderPosition()

        defaultFinanceProcessingStrategy.toTaxTuple(inv)

    }

}