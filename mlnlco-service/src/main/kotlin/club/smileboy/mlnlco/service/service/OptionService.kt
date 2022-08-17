package club.smileboy.mlnlco.service.service

import club.smileboy.mlnlco.service.model.entity.OptionEntity
import club.smileboy.mlnlco.service.model.params.Param
import club.smileboy.mlnlco.service.model.params.Query
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 20:28
 * @description Options Service ...
 **/
@Service
class OptionService : BaseService<OptionEntity> {

    override fun findAllByPageAndQuery(query: Query, pageable: Pageable): Optional<Page<OptionEntity>> {
        TODO("Not yet implemented")
    }

    override fun findAllByQuery(query: Query): Optional<List<OptionEntity>> {
        TODO("Not yet implemented")
    }

    override fun deleteOneById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteListByParam(param: Param) {
        TODO("Not yet implemented")
    }

    override fun updateOneByParam(param: Param) {
        TODO("Not yet implemented")
    }

    override fun updateListByParam(param: Param) {
        TODO("Not yet implemented")
    }

    override fun saveOneByParam(param: Param) {
        TODO("Not yet implemented")
    }
}