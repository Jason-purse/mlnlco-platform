package club.smileboy.mlnlco.service.model.constant;

import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnum;
/**
 * @author FLJ
 * @date 2022/8/16
 * @time 17:13
 * @Description 国际化消息解析类型
 */
public enum I18nMessageResolveType implements ValueEnum<I18nMessageResolveType> {
    /**
     * 消息校验
     */
    VALIDATION("validation."),
    /**
     * 异常
     */
    EXCEPTION("exception.");

    private final String prefix;

    private I18nMessageResolveType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
