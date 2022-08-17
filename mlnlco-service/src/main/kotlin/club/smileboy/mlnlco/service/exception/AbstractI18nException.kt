package club.smileboy.mlnlco.service.exception
/**
 * @author FLJ
 * @date 2022/8/17
 * @time 9:42
 * @Description 抽象的 i18n 异常
 */
abstract class AbstractI18nException(private val code: String,private val defaultMessage: String,private val neededI18n: Boolean = true,private val throwable: Throwable? = null): RuntimeException(defaultMessage, throwable) {

    fun getCode(): String {
        return this.code;
    }

    fun getDefaultMessage(): String {
        return this.defaultMessage;
    }

    fun getNestThrowable(): Throwable? {
        return this.throwable;
    }

    fun isNeededI18n(): Boolean {
        return this.neededI18n;
    }
}