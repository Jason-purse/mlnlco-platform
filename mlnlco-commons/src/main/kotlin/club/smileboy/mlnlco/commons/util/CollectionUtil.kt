package club.smileboy.mlnlco.commons.util

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 16:48
 * @Description 集合操作 ...
 */
object CollectionUtil {

    /**
     * 集合 map 操作
     */
    inline fun <reified T, reified R> List<T>.inlineMapTo(action: (T) -> R): List<R> {
        return this.map { action.invoke(it) }
    }


    fun <T, R> List<T>.mapTo(action: (T) -> R): List<R> {
        return this.map { action.invoke(it) }
    }

    /**
     * 使用真泛型 处理  bean 属性 copy
     */
    inline fun <T, reified R> List<T>.copyBeanProperties(): List<R> {
        return this.map { BeanUtils.transformFrom(it, R::class.java)!! }
    }

    /**
     * 使得可以在Java 环境中调用 ..
     */
    fun <T, R> List<T>.copyBeanProperties(tClass: Class<R>): List<R> {
        return this.mapTo { BeanUtils.transformFrom(it, tClass)!! }
    }

    /**
     * 增加一个 consumer
     */
    fun <T, R> List<T>.copyBeanProperties(tClass: Class<R>, action: R.(T) -> Unit): List<R>
    {
        return this.mapTo { BeanUtils.transformFrom(it, tClass)!!.apply { action(it) } }
    }
}