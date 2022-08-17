package club.smileboy.mlnlco.service.model.constant;

import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnum;

/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 21:17
 * @description 缓存容器类型 ...
 **/
public enum CacheContainerType implements ValueEnum<CacheContainerType> {

    /**
     * redis
     */
    REDIS,

    /**
     * 内存
     */
    MEMORY;

}
