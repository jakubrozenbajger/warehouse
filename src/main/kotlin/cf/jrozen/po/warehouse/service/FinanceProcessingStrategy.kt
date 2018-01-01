package cf.jrozen.po.warehouse.service

import org.springframework.stereotype.Component

interface FinanceProcessingStrategy {
}

@Component
class DefaultFinanceProcessingStrategy : FinanceProcessingStrategy