package club.smileboy.mlnlco.service.model.propertyEnum;
/**
 * @author FLJ
 * @date 2022/8/15
 * @time 12:28
 * @Description 应用类型
 */
public enum ApplicationType implements PropertyEnum<ApplicationType> {

    CLIENT,

    WEBAPP;

    ApplicationType() {

    }
    @Override
    public String asText() {
        return name();
    }
}
