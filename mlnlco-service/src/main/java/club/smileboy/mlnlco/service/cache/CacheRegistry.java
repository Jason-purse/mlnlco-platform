package club.smileboy.mlnlco.service.cache;

import java.io.Serializable;
import java.util.List;

/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 21:14
 * @description 缓存注册表
 *
 * 表示有哪些缓存容器注册到这上面 ....
 *
 * 容器之间的优先顺序 通过它们实现的Order接口来定义, 根据自然数的大小排序 ...
 **/
public interface CacheRegistry<T> {

    /**
     * 注册一个缓存容器
     * @param cacheContainer 缓存容器 ...
     * @param ifDuplicateOverride 如果重复注册是否覆盖 默认是
     * @exception UnsupportedOperationException 如果身份重复不允许 注册 ...(仅当 默认注册不覆盖时抛出异常) ..
     */
    void  registry(T cacheContainer,boolean ifDuplicateOverride);

    /*
     * 获取 注册的容器
     */
    List<T> list(Class<T> containerClass);


    T first(Class<T> containerClass);

    /**
     * 根据 identify 获取缓存容器 ...
     * @param identify 容器身份
     */
    T get(Serializable identify);
}
