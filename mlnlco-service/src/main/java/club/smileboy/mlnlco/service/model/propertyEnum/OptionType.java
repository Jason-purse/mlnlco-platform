package club.smileboy.mlnlco.service.model.propertyEnum;
/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 20:24
 * @description 属性类型(用户可以动态配置的) ...
 **/
public enum OptionType implements PropertyEnum<OptionType> {
    /**
     * 国际化
     */
    I18N;

    @Override
    public String asText() {
        return name();
    }
}
