package club.smileboy.mlnlco.commons.util

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.util.function.Consumer

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 11:30
 * @Description Json Util
 */
class JsonUtil private constructor() {

    private val objectMapper: ObjectMapper = ObjectMapper()

    init {
        objectMapper.registerKotlinModule()
    }

    fun toJson(source: Any): String {
        return objectMapper.writeValueAsString(source)
    }


    fun getObjectMapper(): ObjectMapper = objectMapper


    inline fun <reified T> fromJson(jsonStr: String): T {
        return getObjectMapper().readValue(jsonStr, T::class.java)
    }


    inline fun <reified T> convertTo(source: Any): T {
        return getObjectMapper().convertValue(source, T::class.java)
    }


    companion object {
        val INSTANCE = JsonUtil()

        fun buildJsonUtil(action: Consumer<ObjectMapper>): JsonUtil {
            return JsonUtil().apply {
                action.accept(objectMapper)
            }
        }

        fun registerKotlinSupport(objectMapper: ObjectMapper) {
            objectMapper.registerKotlinModule()
        }
    }
}