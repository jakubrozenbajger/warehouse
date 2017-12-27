package cf.jrozen.po.warehouse

import cf.jrozen.po.warehouse.repository.DocumentRepository
import cf.jrozen.po.warehouse.repository.FileSystemDocumentRepository
import cf.jrozen.po.warehouse.repository.S3DocumentRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class WarehouseAppContext(
        val environment: Environment,
        @Value("warehouse.application.mode")
        val applicationMode: String
) {

    @Bean
    fun documentRepository(): DocumentRepository {
        return if (environment.getProperty(applicationMode) == "dev")
            FileSystemDocumentRepository()
        else S3DocumentRepository()
    }


}