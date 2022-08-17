package club.smileboy.mlnlco.commons.util

import lombok.extern.slf4j.Slf4j
import org.springframework.util.Base64Utils
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

/**
 * @author FLJ
 * @date 2022/8/16
 * @time 13:42
 * @Description 加密 工具类
 *
 * 产生普通加密字符串 /  rsa 国密
 */
object EncryptUtil {

    /**
     * @param secret 盐(进行 md5加密)
     *
     * 通过盐对 随机UUID 进行 SHA-256 加密并通过md5 使用盐对结果进行加密
     * 并通过base64 加密
     *
     * 这就是一个加密字符串 ...
     */
    fun generateNormalEncryptStringsWithBase64(secret: String): String {

        val toString = UuidUtil.generateUUidString()
        val instance = MessageDigest.getInstance("SHA-256")
        instance.update(toString.toByteArray())
        val bytes = instance.digest()
        val list: List<ByteArray> = listOf(bytes,secret.toByteArray())
        val md5 = MessageDigest.getInstance("MD5")
        md5.update(list.flatMap { Iterable { it.iterator() } }.toByteArray())
        return Base64Utils.encodeToString(md5.digest())

    }

    /**
     * 随机生成一个盐 ..
     */
    fun generateNormalEncryptStringsWithBase64(): String {
        val secret: String = UuidUtil.generateUUidString();
        return generateNormalEncryptStringsWithBase64(secret);
    }
}