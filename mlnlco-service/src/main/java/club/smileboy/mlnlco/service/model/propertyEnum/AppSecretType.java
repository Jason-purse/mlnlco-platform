package club.smileboy.mlnlco.service.model.propertyEnum;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 12:45
 * @Description App Secret type
 */
public enum AppSecretType implements PropertyEnum<AppSecretType> {
    RSA,
    NORMAL_SECRET
    ;

    @Override
    public String asText() {
        return name();
    }

}
