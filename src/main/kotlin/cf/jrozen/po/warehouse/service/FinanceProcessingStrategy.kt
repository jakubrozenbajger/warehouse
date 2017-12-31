package cf.jrozen.po.warehouse.service

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

interface FinanceProcessingStrategy {
}

@Component
@Qualifier("standardPS")
class StandardFinanceProcessingStrategy: FinanceProcessingStrategy