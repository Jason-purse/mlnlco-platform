package club.smileboy.mlnlco.commons.util

import org.jetbrains.annotations.Nullable
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 13:46
 * @Description 反射工具类
 */
object ReflectionUtil {
    /**
     * Gets parameterized type.
     *
     * @param superType super type must not be null (super class or super interface)
     * @param genericTypes generic type array
     * @return parameterized type of the interface or null if it is mismatch
     */
    @Nullable
    fun getParameterizedType(
        @org.springframework.lang.NonNull superType: Class<*>,
        vararg genericTypes: Type?
    ): ParameterizedType? {
        org.springframework.util.Assert.notNull(superType, "Interface or super type must not be null")
        var currentType: ParameterizedType? = null
        for (genericType in genericTypes) {
            if (genericType is ParameterizedType) {
                val parameterizedType = genericType
                if (parameterizedType.rawType.typeName == superType.typeName) {
                    currentType = parameterizedType
                    break
                }
            }
        }
        return currentType
    }

    /**
     * Gets parameterized type.
     *
     * @param interfaceType interface type must not be null
     * @param implementationClass implementation class of the interface must not be null
     * @return parameterized type of the interface or null if it is mismatch
     */
    @org.springframework.lang.Nullable
    fun getParameterizedType(
        @org.springframework.lang.NonNull interfaceType: Class<*>,
        implementationClass: Class<*>?
    ): ParameterizedType? {
        org.springframework.util.Assert.notNull(interfaceType, "Interface type must not be null")
        org.springframework.util.Assert.isTrue(interfaceType.isInterface, "The give type must be an interface")
        if (implementationClass == null) {
            // If the super class is Object parent then return null
            return null
        }

        // Get parameterized type
        val currentType = getParameterizedType(interfaceType, *implementationClass.genericInterfaces)
        if (currentType != null) {
            // return the current type
            return currentType
        }
        val superclass = implementationClass.superclass
        return getParameterizedType(interfaceType, superclass)
    }

    /**
     * Gets parameterized type by super class.
     *
     * @param superClassType super class type must not be null
     * @param extensionClass extension class
     * @return parameterized type or null
     */
    @org.springframework.lang.Nullable
    fun getParameterizedTypeBySuperClass(
        @org.springframework.lang.NonNull superClassType: Class<*>, extensionClass: Class<*>?
    ): ParameterizedType? {
        return if (extensionClass == null) {
            null
        } else getParameterizedType(superClassType, extensionClass.genericSuperclass)
    }

    /**
     * Gets field value from Object.
     *
     * @param fieldName fieldName must not be null
     * @param object object must not be null.
     * @return value
     */
    fun getFieldValue(
        @org.springframework.lang.NonNull fieldName: String,
        @org.springframework.lang.NonNull `object`: Any
    ): Any? {
        org.springframework.util.Assert.notNull(fieldName, "FieldName must not be null")
        org.springframework.util.Assert.notNull(`object`, "Object type must not be null")
        var value: Any? = null
        try {
            val firstLetter = fieldName.substring(0, 1).uppercase(Locale.getDefault())
            val getter = "get" + firstLetter + fieldName.substring(1)
            val method = `object`.javaClass.getMethod(getter)
            value = method.invoke(`object`)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return value
    }
}