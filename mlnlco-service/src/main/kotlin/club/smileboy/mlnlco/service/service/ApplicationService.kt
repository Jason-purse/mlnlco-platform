package club.smileboy.mlnlco.service.service

import club.smileboy.mlnlco.commons.util.BeanUtils
import club.smileboy.mlnlco.commons.util.CollectionUtil.copyBeanProperties
import club.smileboy.mlnlco.commons.util.UuidUtil
import club.smileboy.mlnlco.service.model.entity.ApplicationEntity
import club.smileboy.mlnlco.service.model.params.*
import club.smileboy.mlnlco.service.model.propertyEnum.AppSecretType
import club.smileboy.mlnlco.service.repository.ApplicationRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 16:25
 * @Description 应用服务
 */
@Service
class ApplicationService(private val applicationRepository: ApplicationRepository) : BaseService<ApplicationVo> {


    override fun findAllByPageAndQuery(query: Query, pageable: Pageable): Optional<Page<ApplicationVo>> {
        val applicationQuery = query as ApplicationQuery
        val findAll: Page<ApplicationEntity> = if (!applicationQuery.checkNeedToSpecification()) {
            applicationRepository.findAll(pageable)
        } else {
            applicationRepository.findAll(applicationQuery.toSpecification(), pageable)
        }

        if (!findAll.isEmpty) {
            return Optional.of(
                PageImpl(
                    findAll.content.copyBeanProperties(ApplicationDetailVo::class.java),
                    pageable,
                    findAll.totalElements
                )
            )
        }
        return Optional.of(Page.empty(pageable))
    }

    override fun findAllByQuery(query: Query): Optional<List<ApplicationVo>> {
        TODO("Not yet implemented")
    }

    override fun deleteOneByParam(param: Param) {
        TODO("Not yet implemented")
    }

    override fun deleteListByParam(param: Param) {
        TODO("Not yet implemented")
    }

    override fun updateOneByParam(param: Param) {
        var applicationUpdateParam = param as ApplicationUpdateParam
        // 先查询一次..
        applicationRepository.findById(applicationUpdateParam.id).run {
            if (isPresent) {
                // 需要判断是否具有指定的sub / appName 被使用了 ...
                applicationRepository.findOne(applicationUpdateParam.toSpecification()).apply {
                    checkAppLegality(get(),applicationUpdateParam.appName,applicationUpdateParam.sub)
                }
                BeanUtils.updateProperties(applicationUpdateParam, get())
                applicationRepository.save(get())
            } else {
                throw IllegalArgumentException("Unable to find data that needs to be updated !!!")
            }
        }

    }

    override fun updateListByParam(param: Param) {
        TODO("Not yet implemented")
    }

    /**
     * 每次都是一个新的事务
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun saveOneByParam(param: Param) {
        val applicationSaveParam = param as ApplicationSaveParam
        // 判断是否存在相同的appId ,以及应用名称 / sub 的都相同的应用
        applicationRepository.findOne(applicationSaveParam.toSpecification()).apply {
            if (isPresent) {
                checkAppLegality(get(), applicationSaveParam.appName,applicationSaveParam.sub)
            }
        }
        // 根据加密类型产生对应的加密信息 ...
        // 直接保存 ..
        applicationRepository.save(BeanUtils.transformFrom(param, ApplicationEntity::class.java)!!.apply {
            appSecret = this.appSecretType.generateAppSecret()

            // 随机生成 ...(如果冲突了,直接返回前端提示错误即可)
            appId = UuidUtil.generateUUidString()
        })
    }

    private fun checkAppLegality(
        it: ApplicationEntity,
        appName: String,
        sub: String
    ) {
        if (it.appName.equals(appName)) {
            throw IllegalArgumentException("appName must be unique !!!")
        }
        if (it.sub.equals(sub)) {
            throw IllegalArgumentException("sub with appName both must be unique !!!")
        }
    }
}