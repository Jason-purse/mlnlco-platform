package club.smileboy.mlnlco.service.exception

import club.smileboy.mlnlco.service.model.constant.ExceptionValues

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 15:22
 * @Description 系统异常 ...
 */
class SystemException(override val message: String?, throwable: Throwable?, val code: Int = ExceptionValues.SERVER_INTERNAL_ERROR.code, val translate: Boolean = false) : RuntimeException(message,throwable) {

    constructor(message: String?): this(message,null)

    constructor(): this(ExceptionValues.SERVER_INTERNAL_ERROR.name.lowercase(),null)

    constructor(throwable: Throwable?): this(null,throwable)
}