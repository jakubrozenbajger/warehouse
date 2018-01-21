package cf.jrozen.po.warehouse.service

import cf.jrozen.po.warehouse.controller.AbstractControllerTest
import cf.jrozen.po.warehouse.domain.OrderPosition
import cf.jrozen.po.warehouse.randomInvoice
import cf.jrozen.po.warehouse.randomOrder
import cf.jrozen.po.warehouse.randomOrderPosition
import cf.jrozen.po.warehouse.repository.DocumentRepository
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class DocumentArchiveServiceTest : AbstractControllerTest() {

    private lateinit var archiveService: DocumentArchiveService;

    @Autowired
    private lateinit var documentRepository: DocumentRepository;

    @Autowired
    private lateinit var docPrintFac: DocumentPrinterFactory;

    @Before
    fun init() {
        this.archiveService = DocumentArchiveService(documentRepository, docPrintFac)
    }

    @Test
    fun shouldGeneratePdf() {
        val op = HashSet<OrderPosition>()
        for (i in 1..10)
            op.add(randomOrderPosition())
        val order = randomOrder(orderPositions = op)
        archiveService.archive(randomInvoice(order))
    }
}