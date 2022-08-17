package club.smileboy.mlnlco.service.cache;

import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 缓存服务
 * 表示它是一个缓存服务 ....
 */
@Service
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheService {
}
