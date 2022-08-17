package club.smileboy.mlnlco.service.cache.impl;

import club.smileboy.mlnlco.service.cache.Cache;
import club.smileboy.mlnlco.service.cache.CacheContainer;
import club.smileboy.mlnlco.service.cache.CacheService;
import club.smileboy.mlnlco.service.cache.RedisCache;
import club.smileboy.mlnlco.service.model.constant.CacheContainerType;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 22:20
 * @description simple redis Cache
 *  key string
 *  value string
 **/
@Profile({"dev","production"})
@CacheService
public class SimpleRedisCache extends RedisCache<String,String> implements CacheContainer<String,String> {

    public SimpleRedisCache(RedisTemplate<String, String> redisTemplate) {
        super(redisTemplate);
    }

    @NotNull
    @Override
    public String get(@NotNull String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElseThrow(() -> new UnsupportedOperationException(String.format("no value for %s key", key)));
    }

    @Override
    public String getOrNull(@NotNull String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @NotNull
    @Override
    public String getOrDefault(@NotNull String key, @NotNull String defaultValue) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElse(defaultValue);
    }

    @Override
    public String update(@NotNull String key, @NotNull String value) {
        return redisTemplate.opsForValue().getAndSet(key,value);
    }

    @Override
    public void updateOnly(@NotNull String key, @NotNull String value) {
        redisTemplate.opsForValue().set(key,value);
    }


    @Override
    public void updateOnly(String key, String value, long expireMillis) {
        redisTemplate.opsForValue().set(key,value,expireMillis);
    }

    @Override
    public String update(String key, String value, long expireMillis) {
        String result = redisTemplate.opsForValue().get(key);
        redisTemplate.opsForValue().set(key,value,expireMillis);
        return result;
    }

    @Override
    public Cache<String, String> getCacheContainer() {
        return this;
    }

    @Override
    public CacheContainerType getCacheContainerType() {
        return CacheContainerType.REDIS;
    }

    @NotNull
    @Override
    public Serializable getIdentify() {
        return "App_Redis_Cache";
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
