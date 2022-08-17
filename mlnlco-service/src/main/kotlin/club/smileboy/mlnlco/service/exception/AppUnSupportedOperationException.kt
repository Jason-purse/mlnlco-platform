package club.smileboy.mlnlco.service.exception

import club.smileboy.mlnlco.service.model.constant.ExceptionValues

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 9:50
 * @Description  app unsupportedOperationException ... 支持 i18n
 */
class AppUnSupportedOperationException(code: String,defaultMessage: String,neededI18n: Boolean) : AppGenericI18nException(code,defaultMessage,neededI18n) {

    private constructor(): this(ExceptionValues.UN_SUPPORT_ERROR.code.toString(),ExceptionValues.UN_SUPPORT_ERROR.defaultMessage,false)

    companion object {

        fun ofDefault(): AppUnSupportedOperationException {
            return AppUnSupportedOperationException()
        }


        fun ofEnableI18n(code: String,defaultMessage: String): AppUnSupportedOperationException {
            return AppUnSupportedOperationException(code,defaultMessage,true)
        }

        fun ofEnableI18n(code: String): AppUnSupportedOperationException {
            return AppUnSupportedOperationException(code,ExceptionValues.UN_SUPPORT_ERROR.defaultMessage,true)
        }

    }
}

// 通用的i18n 异常 ...
open class AppGenericI18nException(code: String, defaultMessage: String, private val neededI18n: Boolean = true,throwable: Throwable? = null) :
    AbstractI18nException(code, defaultMessage, neededI18n,throwable) {

    constructor(code: String, defaultMessage: String) : this(code, defaultMessage,neededI18n = true) {

    }
}