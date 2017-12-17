package cf.jrozen.po.warehouse.repository

import cf.jrozen.po.warehouse.domain.SaleDocument
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.logging.Logger

abstract class DocumentRepository {

    val logger = Logger.getLogger(this::class::simpleName.name)

    abstract fun saveDocument(sd: Any)
}

@Repository
@Qualifier("s3repo")
class S3DocumentRepository : DocumentRepository() {

    override fun saveDocument(sd: Any) {
        logger.info("Saving document to cloud repository")
    }
}

@Component
@Qualifier("fsRepo")
class FileSystemDocumentRepository : DocumentRepository() {
    override fun saveDocument(sd: Any) {
        logger.info("Saving document to file system repository")

    }
}