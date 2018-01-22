package cf.jrozen.po.warehouse.service

import org.springframework.stereotype.Service

@Service
class CompanyService {

    fun getSellerInfo(): String {
        return "Hutrowania towarów Januszex \n ul. Pionierów Inżynierii Oprogramowania 21/2, \n 98-231 Gryfin, \n Polska"
    }
}