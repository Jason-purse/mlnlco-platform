package club.smileboy.mlnlco.service.model.propertyEnum;
/**
 * @author FLJ
 * @date 2022/8/15
 * @time 12:41
 * @Description 图标类型
 */
public enum IcoType implements PropertyEnum<IcoType> {
    BASE64,
    URL,
    NONE;

    @Override
    public String asText() {
        return name();
    }
}
