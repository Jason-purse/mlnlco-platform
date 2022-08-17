package club.smileboy.mlnlco.service.cache;

import org.springframework.lang.Nullable;

/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 21:24
 * @description 回收缓存容器
 *
 * 支持 缓存过期策略
 **/
public interface  RecycleCache<K,V> extends Cache<K,V> {
    /**
     * 保留有效时间
     * @param k k
     * @param v v
     * @param expireMillis 过期时间(毫秒值)
     */
    void updateOnly(K k,V v,long expireMillis);

    /**
     * 保留有效时间并返回之前的值
     * @param k k
     * @param v v
     * @param expireMillis 过期时间(毫秒值)
     * @return 之前值 null / value
     */
    @Nullable
    V update(K k, V v, long expireMillis);
}
