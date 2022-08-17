package club.smileboy.mlnlco.service.model.propertyEnum;
/**
 * @author FLJ
 * @date 2022/8/17
 * @time 13:55
 * @Description Sex type
 */
public enum SexType implements PropertyEnum<SexType> {

    MAN,
    WOMAN
    ;

    @Override
    public String asText() {
        return name();
    }
}
