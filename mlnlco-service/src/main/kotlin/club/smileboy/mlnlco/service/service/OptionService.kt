package club.smileboy.mlnlco.service.service

import club.smileboy.mlnlco.commons.util.JWTUtil
import club.smileboy.mlnlco.service.exception.AppUnSupportedOperationException
import club.smileboy.mlnlco.service.model.constant.CommonOptions
import club.smileboy.mlnlco.service.model.entity.OptionEntity
import club.smileboy.mlnlco.service.model.params.Param
import club.smileboy.mlnlco.service.model.params.Query
import club.smileboy.mlnlco.service.repository.OptionRepository
import org.springframework.data.domain.Example
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
class OptionService(private val optionRepository: OptionRepository) : BaseService<OptionEntity> {

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

    fun findJwtCryptType(): JWTUtil.CryptSecretType {

        return optionRepository.findOne(Example.of(OptionEntity().apply {
            optKey = CommonOptions.JWT_CRYPT_TYPE.keyName
        })).run {
            if (isPresent) {
                try {
                    if (get().enable) {
                        return@run JWTUtil.CryptSecretType.valueOf(get().optValue)
                    }
                } catch (e: Exception) {
                    // pass
                }
            }
            throw AppUnSupportedOperationException.ofEnableI18n("authorize.error.option")
        }
    }

    fun findJwtEncryptSecret(): String {
        return optionRepository.findOne(Example.of(OptionEntity().apply {
            optKey = CommonOptions.JWT_CRYPT.keyName
        })).run {
            if (isPresent) {
                if (get().enable) {
                    return@run get().optValue
                }
            }
            throw AppUnSupportedOperationException.ofEnableI18n("authorize.error.option")
        }
    }

    fun findJwsEncryptSecret(): String {
        return optionRepository.findOne(Example.of(OptionEntity().apply {
            optKey = CommonOptions.JWS_CRYPT.keyName
        })).run {
            if (isPresent && get().enable) {
                return@run get().optValue
            }
            throw AppUnSupportedOperationException.ofEnableI18n("authorize.error.option")
        }
    }

    fun findJwtSignCryptType(): JWTUtil.EncryptAlgorithmType {
        return optionRepository.findOne(Example.of(OptionEntity().apply {
            optKey = CommonOptions.JWT_SIGN_CRYPT_TYPE.keyName
        }))
            .run {
                if (isPresent && get().enable) {
                    try {
                        return@run JWTUtil.EncryptAlgorithmType.valueOf(get().optValue)
                    } catch (e: Exception) {
                        // pass
                    }
                }

                throw AppUnSupportedOperationException.ofEnableI18n("authorize.error.option")
            }
    }
}