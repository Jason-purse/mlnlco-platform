package club.smileboy.mlnlco.service.cache;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 21:10
 * @description Cache ... 抽象 ..
 **/
public interface Cache<K,V> {

    /**
     * 针对对应的Key 获取对应的 值
     * @param k key
     * @return value
     */
    @NonNull V get(@NonNull K k);

    /**
     * 针对给定的key 返回给定的值
     * @param k key
     * @return value or null
     */
    @Nullable V getOrNull(@NonNull K k);

    /**
     * 针对对应的Key 获取对应的值
     * @param k key
     * @param defaultValue 默认值
     * @return value or default value
     */
    @NonNull V getOrDefault(@NonNull K k,@NonNull V defaultValue);

    /**
     * 更新一个Key对应的值
     * @param k key
     * @param v value
     * @return 之前的旧值
     */
    @Nullable V update(@NonNull K k,@NonNull V v);

    /**
     * 仅仅更新
     * @param k key
     * @param v value
     */
    void updateOnly(@NonNull K k,@NonNull V v);

    /**
     * 如果支持过期,则可以转换为 RecycleCache
     */
    default boolean isSupportExpire() {
        return this instanceof RecycleCache;
    }
}
