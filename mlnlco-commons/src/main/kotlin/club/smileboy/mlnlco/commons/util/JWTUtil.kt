package club.smileboy.mlnlco.commons.util

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator

/**
 * @author FLJ
 * @date 2022/8/18
 * @time 9:38
 * @Description jwt util
 */
class JWTUtil {

    /**
     * jwt entity
     * 通过它生成 jwtToken
     */
    public interface JwtEntity {

        fun <T> generateJwtToken(jwtBuilder: JwtBuilder<T>): String {
            throw UnsupportedOperationException("no implementation !!!")
        }

        abstract fun generateJwtTokenByDefault(jwtBuilder: JwtBuilder<JWTCreator.Builder>): String;

        abstract fun getCryptSecretType(): CryptSecretType;

        abstract fun getEncryptAlgorithmType(): EncryptAlgorithmType;
    }

    public interface JwtBuilder<T> {

        companion object {
            fun ofDefault(): JwtBuilder<JWTCreator.Builder> {
                return SimpleJwtBuilder()
            }
        }

        /**
         * 具体的 依赖的第三方库的 builder
         */
        fun getCoreBuilder(): T;
    }

    class SimpleJwtBuilder : JwtBuilder<JWTCreator.Builder> {

        override fun getCoreBuilder(): JWTCreator.Builder {
            return JWT.create();
        }
    }

    enum class CryptSecretType {
        JWT,
        JWS
    }

    enum class EncryptAlgorithmType {
        HS256
    }

    companion object {
        // 进行Jwt 串构建 ...

        fun generateJwtTokenByDefault(jwtEntity: JwtEntity): String {
            return jwtEntity.generateJwtTokenByDefault(JwtBuilder.ofDefault());
        }

        /**
         * 生成 jwt token
         */
        fun <T> generateJwtToken(jwtEntity: JwtEntity,jwtBuilder: JwtBuilder<T>): String {
            return jwtEntity.generateJwtToken(jwtBuilder);
        }
    }
}