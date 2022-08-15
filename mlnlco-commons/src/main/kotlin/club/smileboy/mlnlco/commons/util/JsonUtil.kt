package club.smileboy.mlnlco.commons.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 11:30
 * @Description Json Util
 */
object JsonUtil {

    private val objectMapper: ObjectMapper = ObjectMapper()

    init {
        objectMapper.registerKotlinModule()
    }

    fun toJson(source: Any): String {
        return objectMapper.writeValueAsString(source)
    }


    fun  getObjectMapper(): ObjectMapper = objectMapper



    inline fun <reified T> fromJson(jsonStr: String): T {
        return getObjectMapper().readValue(jsonStr,T::class.java)
    }


    inline fun <reified T> convertTo(source: Any): T {
        return getObjectMapper().convertValue(source,T::class.java)
    }



}