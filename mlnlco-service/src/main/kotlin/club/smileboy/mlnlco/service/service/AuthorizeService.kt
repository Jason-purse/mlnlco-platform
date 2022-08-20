package club.smileboy.mlnlco.service.service

import club.smileboy.mlnlco.commons.util.DateUtil
import club.smileboy.mlnlco.commons.util.JWTUtil
import club.smileboy.mlnlco.commons.util.JsonUtil
import club.smileboy.mlnlco.commons.util.UuidUtil
import club.smileboy.mlnlco.service.exception.AppUnSupportedOperationException
import club.smileboy.mlnlco.service.model.constant.AuthorizationType
import club.smileboy.mlnlco.service.model.entity.ApplicationEntity
import club.smileboy.mlnlco.service.model.params.*
import club.smileboy.mlnlco.service.model.property.PropertySerializerComposite
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Service

/**
 * @author JASONJ
 * @date 2022/8/17
 * @time 21:34
 * @description 认证服务
 **/

@Service
class AuthorizeService(
    private val optionService: OptionService,
    private val applicationService: ApplicationService,
    private val userService: UserService
) {
    /**
     * 授权 动作
     */
    fun authorize(param: Param): AuthorizeVo {

        // 拿到对应的app 的secret 进行 jwt 令牌解密 ...
        val authorizeParam = param as AuthorizeParam
        return JWTAuthorizeVo().apply {
            token = applicationService.findOneByAppId(authorizeParam.appId).run {
                val cryptType = optionService.findJwtCryptType()
                val secret = when (cryptType) {
                    JWTUtil.CryptSecretType.JWT -> optionService.findJwtEncryptSecret()
                    JWTUtil.CryptSecretType.JWS -> optionService.findJwsEncryptSecret()
                }

                val findJwtSignCryptType = optionService.findJwtSignCryptType()

                // 查询用户信息
                userService.findOneByUserOriginAndUsername(authorizeParam.origin, authorizeParam.username).let {

                    // 表示存在 ...
                    // 开始构建 jwt token
                    JWTUtil.generateJwtTokenByDefault(
                        BaseApplicationJwtEntity(
                            this,
                            it,
                            authorizeParam,
                            cryptType,
                            secret,
                            findJwtSignCryptType,
                            if (authorizeParam.isFullPlatFormToken) applicationService.findAllApplicationSub().apply {
                                if (isEmpty()) {
                                    throw AppUnSupportedOperationException.ofEnableI18n("app.empty.jwt")
                                }
                            } else listOf(
                                this.sub
                            )
                        )
                    )
                }
            }
        }
    }


    class BaseApplicationJwtEntity(
        private val applicationInfo: ApplicationDetailVo,
        private val userInfo: AppUserDetailVo,
        private val authorizeParam: AuthorizeParam,
        private val cryptSecretType: JWTUtil.CryptSecretType,
        private val secret: String,
        private val encryptAlgorithmType: JWTUtil.EncryptAlgorithmType,
        // 受众列表
        private val audList: List<String>
    ) : JWTUtil.JwtEntity {
        override fun generateJwtTokenByDefault(jwtBuilder: JWTUtil.JwtBuilder<JWTCreator.Builder>): String {
            // header
            doJwtHeader(jwtBuilder.getCoreBuilder());
            var builder = jwtBuilder.getCoreBuilder()
            // 根据授权类型 构造不同的jwt 令牌
            // 拿取用户的信息
            builder.withClaim(
                "principal", JsonUtil.buildJsonUtil {
                    PropertySerializerComposite.configure(it)
                }.toJson(userInfo.apply {
                    // 保护密码 ..
                    password = ""
                })
            )

            builder.withClaim("sub", applicationInfo.sub)
            builder.withClaim("appId", applicationInfo.appId)
            builder.withClaim("iss", "www.mlnlco.smileboy.club")
            // 受众 ...
            builder.withClaim("aud", audList)
            // 30 分钟 ..
            builder.withClaim("exp", DateUtil.plus(30 * 1000 * 60))

            // jwt token unique id ,暂时先不用
            builder.withClaim("jti", UuidUtil.generateUUidString())

            return doSign(builder);
        }


        private fun doJwtHeader(builder: JWTCreator.Builder) {
            builder.withHeader(
                mapOf(
                    "typ" to cryptSecretType.name,
                    "alg" to encryptAlgorithmType.name
                )
            )
        }

        fun doSign(builder: JWTCreator.Builder): String {
            return when (encryptAlgorithmType) {

                JWTUtil.EncryptAlgorithmType.HS256 -> builder.sign(Algorithm.HMAC256(secret))
                // never occur ...
                else -> throw AppUnSupportedOperationException.ofEnableI18n(
                    "authorize.jwt.generate.error",
                    encryptAlgorithmType
                )
            }
        }

        override fun getCryptSecretType(): JWTUtil.CryptSecretType {
            return this.cryptSecretType;
        }

        override fun getEncryptAlgorithmType(): JWTUtil.EncryptAlgorithmType {
            return encryptAlgorithmType;
        }
    }
}

