package club.smileboy.mlnlco.service.cache;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 21:30
 * @description 简单Redis 缓存容器
 **/
public abstract  class RedisCache<K,V> implements RecycleCache<K,V> {
    /**
     * internal redis template
     */
    protected RedisTemplate<K,V> redisTemplate;

    protected RedisCache(RedisTemplate<K,V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
