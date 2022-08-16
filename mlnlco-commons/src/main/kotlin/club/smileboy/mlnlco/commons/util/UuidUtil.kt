package club.smileboy.mlnlco.commons.util

import org.springframework.util.AlternativeJdkIdGenerator

/**
 * @author FLJ
 * @date 2022/8/16
 * @time 15:12
 * @Description uuid util
 */
object UuidUtil {

    private val  uuidGenerator: AlternativeJdkIdGenerator = AlternativeJdkIdGenerator()

    /**
     * 生成UUID 字符串
     */
    fun generateUUidString(): String {
        return uuidGenerator.generateId().toString()
    }
}