package club.smileboy.mlnlco.service.service

import club.smileboy.mlnlco.commons.util.CollectionUtil
import club.smileboy.mlnlco.commons.util.CollectionUtil.copyBeanProperties
import club.smileboy.mlnlco.service.model.entity.ApplicationEntity
import club.smileboy.mlnlco.service.model.params.ApplicationQuery
import club.smileboy.mlnlco.service.model.params.ApplicationVo
import club.smileboy.mlnlco.service.model.params.Param
import club.smileboy.mlnlco.service.model.params.Query
import club.smileboy.mlnlco.service.repository.ApplicationRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*
/**
 * @author FLJ
 * @date 2022/8/15
 * @time 16:25
 * @Description 应用服务
 */
@Service
class ApplicationService(private val applicationRepository: ApplicationRepository): BaseService<ApplicationVo> {


    override fun findAllByPageAndQuery(query: Query, pageable: Pageable): Optional<Page<ApplicationVo>> {
        val applicationQuery = query as ApplicationQuery
        var findAll: Page<ApplicationEntity> = applicationRepository.findAll(applicationQuery.convert(),pageable)
        if(!findAll.isEmpty) {
            return Optional.of(
                PageImpl(findAll.content.copyBeanProperties(),pageable,findAll.totalElements)
            )
        }
        return Optional.of(Page.empty(pageable))
    }

    override fun findAllByQuery(query: Query): Optional<List<ApplicationVo>> {
        TODO("Not yet implemented")
    }

    override fun deleteOneByQuery(param: Param) {
        TODO("Not yet implemented")
    }

    override fun deleteListByQuery(param: Param) {
        TODO("Not yet implemented")
    }

    override fun updateOneByQuery(param: Param) {
        TODO("Not yet implemented")
    }

    override fun updateListByQuery(param: Param) {
        TODO("Not yet implemented")
    }
}