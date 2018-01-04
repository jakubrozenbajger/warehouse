package cf.jrozen.po.warehouse.repository

import org.springframework.stereotype.Repository
import java.io.OutputStream
import java.util.logging.Logger

abstract class DocumentRepository {

    val logger: Logger = Logger.getLogger(this.javaClass.simpleName)

    abstract fun saveDocument(name: String, sd: OutputStream)
}

@Repository
class S3DocumentRepository : DocumentRepository() {

    override fun saveDocument(name: String, sd: OutputStream) {
        logger.info("Saving document to cloud repository")

    }
}

@Repository
class FileSystemDocumentRepository : DocumentRepository() {
    override fun saveDocument(name: String, sd: OutputStream) {
        logger.info("Saving document to file system repository")

    }
}

