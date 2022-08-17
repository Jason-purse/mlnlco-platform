package club.smileboy.mlnlco.service.service

import club.smileboy.mlnlco.commons.util.BeanUtils
import club.smileboy.mlnlco.commons.util.CollectionUtil.copyBeanProperties
import club.smileboy.mlnlco.commons.util.UuidUtil
import club.smileboy.mlnlco.service.exception.AppUnSupportedOperationException
import club.smileboy.mlnlco.service.model.entity.ApplicationEntity
import club.smileboy.mlnlco.service.model.params.*
import club.smileboy.mlnlco.service.model.propertyEnum.AppSecretType
import club.smileboy.mlnlco.service.repository.ApplicationRepository
import club.smileboy.mlnlco.service.util.PageUtil.Companion.withSort
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.config.TransactionManagementConfigUtils
import org.springframework.transaction.interceptor.TransactionAspectSupport
import java.util.*

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 16:25
 * @Description 应用服务
 */
@Service
class ApplicationService(private val applicationRepository: ApplicationRepository) : BaseService<ApplicationVo> {

    /**
     * 查询所有的应用信息并分页
     */
    override fun findAllByPageAndQuery(query: Query, pageable: Pageable): Optional<Page<ApplicationVo>> {
        val applicationQuery = query as ApplicationQuery

        // 默认通过sort 排序(时间倒叙)
        val queryPageable: PageRequest = pageable.withSort(Sort.by(Sort.Direction.DESC, "updateTime"))

        val findAll: Page<ApplicationEntity> = if (!applicationQuery.checkNeedToSpecification()) {
            applicationRepository.findAll(queryPageable)
        } else {
            applicationRepository.findAll(applicationQuery.toSpecification(), queryPageable)
        }

        if (!findAll.isEmpty) {
            return Optional.of(
                PageImpl(
                    findAll.content.copyBeanProperties(ApplicationDetailVo::class.java),
                    queryPageable,
                    findAll.totalElements
                )
            )
        }
        return Optional.of(Page.empty(queryPageable))
    }

    override fun findAllByQuery(query: Query): Optional<List<ApplicationVo>> {
        TODO("Not yet implemented")
    }

    @Transactional(
        propagation = Propagation.REQUIRES_NEW
    )
    override fun deleteOneById(id: Int) {
        try {
            // 直接删除即可 ...
            applicationRepository.deleteById(id)
        } catch (e: DataAccessException) {
            // pass (删除成功)
            // 报错之后,表示没有这个数据,rollback 即可 ...
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly()
        }
    }

    override fun deleteListByParam(param: Param) {
        TODO("Not yet implemented")
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun updateOneByParam(param: Param) {
        var applicationUpdateParam = param as ApplicationUpdateParam
        // 先查询一次..
        applicationRepository.findById(applicationUpdateParam.id).run {
            if (isPresent) {
                // 需要判断是否具有指定的sub / appName 被使用了 ...
                // 首先判断自己是否相等(如果相等,则不继续判断,否则判断是否存在对应的名称 / sub 被占用)
                if (checkAppLegality(get(), applicationUpdateParam.appName, applicationUpdateParam.sub, true)) {
                    if(applicationRepository.count(applicationUpdateParam.toSpecification()) > 0) {
                        throw AppUnSupportedOperationException.ofEnableI18n("app.name_sub")
                    }
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
                checkAppLegality(get(), applicationSaveParam.appName, applicationSaveParam.sub)
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

    /**
     * 判断app 相关信息是否冲突 ...
     */
    private fun checkAppLegality(
        it: ApplicationEntity,
        appName: String,
        sub: String,
        isSelf: Boolean = false
    ): Boolean {

        if (it.appName.equals(appName)) {
            if(!isSelf) {
                throw AppUnSupportedOperationException.ofEnableI18n("app.name")
            }

            // 表示冲突
            return true;
        }
        if (it.sub.equals(sub)) {
            if(!isSelf) {
                throw AppUnSupportedOperationException.ofEnableI18n("app.sub")
            }
            return true;
        }

        // 表示不冲突 ...
        return false;
    }
}