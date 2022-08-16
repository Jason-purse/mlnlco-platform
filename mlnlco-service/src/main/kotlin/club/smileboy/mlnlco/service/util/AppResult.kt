package club.smileboy.mlnlco.service.util

import club.smileboy.mlnlco.service.model.constant.ExceptionValues
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * @author FLJ
 * @date 2022/8/16
 * @time 12:36
 * @Description App result
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class AppResult<T>(
    /**
     * code
     */
    val code: Int,
    /**
     * message
     */
    val message: String,
    /**
     * result
     */
    val result: T? = null) {

    companion object {
        fun <T> success(result: T): AppResult<T> {
            return AppResult(
                ExceptionValues.SUCCESS.code,
                ExceptionValues.SUCCESS.name.lowercase(),result)
        }

        fun success(): AppResult<*> {
            return AppResult<Any?>(ExceptionValues.SUCCESS.code, ExceptionValues.SUCCESS.name.lowercase())
        }

        fun error(code: Int,message: String): AppResult<*> {
            return AppResult<Any?>(code,message)
        }
    }
}