package club.smileboy.mlnlco.commons.util
/**
 * @author FLJ
 * @date 2022/8/16
 * @time 11:31
 * @Description 字符串Util
 */
object StringUtil {
    fun allStringIsEmpty(vararg args: String): Boolean {
        return args.isNotEmpty() && args.all { it.isNotBlank() }
    }


}