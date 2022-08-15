package club.smileboy.mlnlco.commons.util

object ArrayUtil {
    /**
     * 判断 数组是否为空
     */
    fun <T> isEmpty(array: Array<out T>?): Boolean {

        if(array == null || array.isEmpty()) {
            return true;
        }
        return false;
    }
}