package club.smileboy.mlnlco.service.model.propertyEnum

import java.util.*
import javax.persistence.AttributeConverter
import javax.persistence.Converter

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 13:03
 * @Description 应用 密钥
 */
sealed class AppSecret {
    /**
     * 加密类型
     */
    abstract fun getSecretType(): AppSecretType

    /**
     * 不包括类型前缀的 加密文本
     */
    abstract fun getSecretAsText(): String

    /**
     * 包括类型前缀 ... 加密文本
     */
    abstract fun getSecretAsFullText(): String


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return Objects.hashCode(getSecretAsFullText())
    }

    companion object {
        const val delimiter: String = ":";

        fun of(appSecretType: AppSecretType, value: String): Optional<AppSecret> {
            return Optional.ofNullable(
                when (appSecretType) {
                    AppSecretType.RSA -> RSASecret(value)
                    AppSecretType.NORMAL_SECRET -> NormalSecret(value)
                }
            )
        }

        private fun of(appSecretTypeStr: String, value: String): Optional<AppSecret> {
            return Optional.ofNullable(
                when (appSecretTypeStr) {
                    AppSecretType.RSA.asText() -> RSASecret(value)
                    AppSecretType.NORMAL_SECRET.asText() -> NormalSecret(value)
                    else -> null
                }
            )
        }

        fun of(appSecretStr: String): Optional<AppSecret> {
            appSecretStr.split(delimiter).let {
                if (it.size < 2) {
                    return Optional.empty()
                }
                return of(it[0], appSecretStr.substring(it[0].length))
            }
        }

        /**
         * App SecretAttributeConverter
         */
        @Converter(autoApply = true)
        public class AppSecretAttributeConverter : AttributeConverter<AppSecret, String> {
            override fun convertToDatabaseColumn(appSecret: AppSecret?): String? {
                return appSecret?.getSecretAsText();
            }

            override fun convertToEntityAttribute(value: String?): AppSecret? {
                return value?.let { of(value).orElseGet(null) }
            }
        }


        class RSASecret(value: String, values: List<String> = value.split(delimiter)) : AppSecret() {

            private val pubKey: String = values[0].apply {
                ifEmpty {
                    throw IllegalArgumentException("pubKey must not be null !!")
                }
            }
            private val priKey: String = values[1].apply {
                ifEmpty { throw IllegalArgumentException("priKey must not be null !!") }
            }
            private val originKey: String = value

            override fun getSecretType(): AppSecretType {
                return AppSecretType.RSA;
            }

            fun getPubKey(): String {
                return pubKey;
            }

            fun getPriKey(): String {
                return priKey;
            }

            override fun getSecretAsText(): String {
                return originKey;
            }

            override fun getSecretAsFullText(): String {
                return "${getSecretType()}${delimiter}${getSecretAsText()}"
            }
        }

        class NormalSecret(value: String) : AppSecret() {
            private val secret: String = value;
            override fun getSecretType(): AppSecretType {
                return AppSecretType.NORMAL_SECRET;
            }

            override fun getSecretAsText(): String {
                return secret;
            }

            override fun getSecretAsFullText(): String {
                return "${getSecretType()}${delimiter}${getSecretAsText()}"
            }
        }
    }

}


