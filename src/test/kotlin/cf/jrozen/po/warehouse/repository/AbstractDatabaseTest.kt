package cf.jrozen.po.warehouse.repository

import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@DataJpaTest
@ActiveProfiles("test")
@Rollback
@Transactional
@TestExecutionListeners(value = [
    DependencyInjectionTestExecutionListener::class,
    TransactionalTestExecutionListener::class])
abstract class AbstractDatabaseTest