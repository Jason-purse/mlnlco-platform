package club.smileboy.mlnlco.service.util

import club.smileboy.mlnlco.commons.util.JsonUtil
import club.smileboy.mlnlco.service.exception.SystemException
import org.springframework.beans.MutablePropertyValues
import org.springframework.beans.PropertyValues
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMethod
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import javax.servlet.http.HttpServletRequest

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 14:57
 * @Description 判断是否为Get 请求
 */
object RequestUtil {

    fun isGetRequest(request: HttpServletRequest): Boolean {
        try {
            return RequestMethod.valueOf(request.method) == RequestMethod.GET;
        } catch (e: Exception) {
            // pass
        }
        return false;
    }

    /**
     * 媒体类型是否包括在内
     * @param request 携带了content-type 的 请求
     * @param mediaType 判断request的content-type 是否在这个media-type的范围中 ...
     */
    fun mediaTypeInclude(request: HttpServletRequest, mediaType: MediaType): Boolean {
        val contentType = request.contentType
        return mediaType.includes(MediaType.valueOf(contentType));
    }

    fun convertPropertyValues(request: HttpServletRequest, isStream: Boolean, mediaType: MediaType?): PropertyValues {
        if (isStream) {
            if (!mediaTypeInclude(request, mediaType!!)) {
                throw IllegalArgumentException("Only the ${mediaType.type} content-type and the subtypes it contains are supported")
            }
            var toByteArray: ByteArray? = null;
            try {
                val inputStreamResource = InputStreamResource(request.inputStream)
                val readableChannel = inputStreamResource.readableChannel()
                val allocate = ByteBuffer.allocate(1024)
                val outStream = ByteArrayOutputStream()
                var length = 0;
                if (request.inputStream.available() > 0) {
                    do {
                        length = readableChannel.read(allocate);
                        if (length <= 0) {
                            break;
                        }
                        outStream.write(allocate.array(), 0, length);
                    } while (length > 0);
                }

                toByteArray = outStream.toByteArray()
                outStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
                throw SystemException()
            }

            // 目前是默认的 .. 稍后再改
            val data: Map<String, Any> = JsonUtil.INSTANCE.fromJson(String(toByteArray!!))
            return MutablePropertyValues(data)
        } else {
            return MutablePropertyValues(request.parameterMap)
        }
    }
}