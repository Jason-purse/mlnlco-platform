package club.smileboy.mlnlco.service.service

import club.smileboy.mlnlco.commons.util.BeanUtils
import club.smileboy.mlnlco.commons.util.CollectionUtil.copyBeanProperties
import club.smileboy.mlnlco.service.exception.AppUnSupportedOperationException
import club.smileboy.mlnlco.service.model.entity.AppUserEntity
import club.smileboy.mlnlco.service.model.params.*
import club.smileboy.mlnlco.service.model.propertyEnum.UserOrigin
import club.smileboy.mlnlco.service.repository.UserRepository
import org.springframework.dao.DataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.interceptor.TransactionAspectSupport
import org.springframework.util.StringUtils
import java.util.*

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 14:06
 * @Description 用户service ...
 */
@Service
class UserService(private val userRepository: UserRepository) :
    BaseService<AppUserVo> {


    override fun findAllByPageAndQuery(query: Query, pageable: Pageable): Optional<Page<AppUserVo>> {
        val userQuery = query as UserQuery
        // 仅当 origin 单独存在的时候这样做 ..
        return if (userQuery.checkNeedToSpecification()) {
            userRepository.findAll(userQuery.toSpecification(), pageable).run { copyBeanProperties() }
        } else {
            userRepository.findAll(pageable).run { copyBeanProperties() }
        }
    }

    fun Page<AppUserEntity>.copyBeanProperties(): Optional<Page<AppUserVo>> {
        if (!isEmpty) {
            // 表示存在 ..
            return Optional.of(
                PageImpl(
                    content.copyBeanProperties(AppUserDetailVo::class.java),
                    pageable,
                    totalElements
                )
            )
        }
        return Optional.empty();
    }

    override fun findAllByQuery(query: Query): Optional<List<AppUserVo>> {
        TODO("Not yet implemented")
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun deleteOneById(id: Int) {
        try {
            // 直接删除即可 ...
            userRepository.deleteById(id)
        } catch (e: DataAccessException) {
            // pass (删除成功)
            // 报错之后,表示没有这个数据,rollback 即可 ...
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly()
        }
    }

    override fun deleteListByParam(param: Param) {
        TODO("Not yet implemented")
    }

    override fun updateOneByParam(param: Param) {
        val userUpdateParam = param as UserUpdateParam
        // 根据id 查询用户信息
        userRepository.findById(userUpdateParam.id).run {
            if (isEmpty) {
                throw AppUnSupportedOperationException.ofEnableI18n("user.empty.update")
            }

            if (checkUserLegality(get(), userUpdateParam.username, userUpdateParam.origin, true)) {
                // 在 不和需要更新的值相同时,则查找是否存在 同名 user info ...
                if (userUpdateParam.checkNeedToSpecification()) {
                    userRepository.findOne(userUpdateParam.toSpecification()).run {
                        if (!isEmpty) {
                            checkUserLegality(get(), userUpdateParam.username, userUpdateParam.origin, false)
                        }
                    }
                }
            }
            // 否则直接更新
            userRepository.save(BeanUtils.transformFrom(userUpdateParam, AppUserEntity::class.java)!!.apply {
                if (StringUtils.hasText(userUpdateParam.phone.info)) {
                    phone = userUpdateParam.phone.info
                }
            })
        }
}

/**
 * 不合法,为true,否则为false
 */
private fun checkUserLegality(user: AppUserEntity, username: String, origin: UserOrigin, isSelf: Boolean): Boolean {
    if (!user.username.equals(username) || !user.originType.equals(origin)) {
        if (!isSelf) {
            throw AppUnSupportedOperationException.ofEnableI18n("user.name_origin")
        }
        return true;
    }

    return false;
}

override fun updateListByParam(param: Param) {
    TODO("Not yet implemented")
}

@Transactional(propagation = Propagation.REQUIRES_NEW)
override fun saveOneByParam(param: Param) {
    val userSaveParam = param as UserSaveParam
    if (userSaveParam.checkNeedToSpecification()) {
        userRepository.findOne(userSaveParam.toSpecification()).run {
            if (!isEmpty) {
                // 判断,如果当前这个信息存在,则抛出异常
                throw AppUnSupportedOperationException.ofEnableI18n("user.name_origin")
            }
            userRepository.save(BeanUtils.transformFrom(userSaveParam, AppUserEntity::class.java)!!.apply {
                if (StringUtils.hasText(userSaveParam.phone.info)) {
                    // 电话信息单独处理 ...
                    phone = userSaveParam.phone.info
                }
            })
        }
    }
}
}