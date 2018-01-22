package cf.jrozen.po.warehouse.repository

import java.io.*
import java.util.logging.Logger

abstract class DocumentRepository {

    val logger: Logger = Logger.getLogger(this.javaClass.simpleName)

    abstract fun saveDocument(name: String, sd: ByteArrayOutputStream)
    abstract fun getDocument(name: String): ByteArray
}

/**
 * [S3DocumentRepository] stores documents
 */
class S3DocumentRepository : DocumentRepository() {
    override fun getDocument(name: String): ByteArray {
        TODO("not implemented")
    }

    override fun saveDocument(name: String, sd: ByteArrayOutputStream) {
        logger.info("Saving document to cloud repository")

    }
}

/**
 * [FileSystemDocumentRepository] stores system data file
 */
class FileSystemDocumentRepository : DocumentRepository() {
    override fun saveDocument(name: String, sd: ByteArrayOutputStream) {
        logger.info("Saving document to file system repository")
        val file = File("/tmp/$name.pdf")
        val fos = FileOutputStream(file)
        file.createNewFile()
        sd.writeTo(fos)
        sd.close()
        fos.close()
    }

    override fun getDocument(name: String): ByteArray {
        logger.info("Getting document from file system repository")
        val file = java.io.File("/tmp/$name.pdf")
        return java.io.FileInputStream(file).readBytes()//readAllBytes()
//        return java.io.FileInputStream(file).readAllBytes()
    }
}

