package club.smileboy.mlnlco.service.cache;

import club.smileboy.mlnlco.service.model.constant.CacheContainerType;
import org.springframework.core.Ordered;
import org.springframework.lang.NonNull;

import java.io.Serializable;
/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 21:43
 * @description 主要注册到 缓存注册机中,子类可以对这个缓存容器做一些自定义操作
 * 例如 {@code SimpleBaseBeanFactoryCacheRegistry} 通过ioc 的依赖注册 可以实现 bean的生命周期流程 ...
 **/
public interface CacheContainer<K,V>  extends Ordered {

    /**
     * 获取缓存容器
     * @return 缓存容器
     */
    Cache<K,V> getCacheContainer();

    /**
     * 获取缓存容器类型
     */
    CacheContainerType getCacheContainerType();

    /**
     * 这个缓存容器的唯一标识
     */
    @NonNull
    Serializable getIdentify();
}
