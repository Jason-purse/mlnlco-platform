package club.smileboy.mlnlco.commons.util

import java.lang.reflect.Modifier

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 15:54
 * @Description Class Util
 */
object ClassUtil {

    fun Class<out Any>.ifNoDefaultConstructor(action: () -> Unit) {
        try {
            this.getDeclaredConstructor()
        } catch (e: Exception) {
            action()
        }
    }

    fun Class<out Any>.ifAbstract(action: () -> Unit) {
        if (Modifier.isAbstract(this.modifiers)) {
            action()
        }
    }
}