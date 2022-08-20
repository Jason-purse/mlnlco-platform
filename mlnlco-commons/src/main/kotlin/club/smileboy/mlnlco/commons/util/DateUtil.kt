package club.smileboy.mlnlco.commons.util

import java.util.*

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 13:33
 * @Description date util
 */
object DateUtil {
    /**
     * 当前时间
     */
    fun now(): Date {
        return Date()
    }

    fun plus(millis: Long): Long {
        return now().toInstant().plusMillis(millis).toEpochMilli()
    }
}