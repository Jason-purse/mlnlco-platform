package club.smileboy.mlnlco.service.model.propertyEnum;
/**
 * @author FLJ
 * @date 2022/8/17
 * @time 13:38
 * @Description 用户来源
 */
public enum UserOrigin implements PropertyEnum<UserOrigin> {
    /**
     * 本地注册
     */
    NATIVE_REGISTER,
    /**
     * facebook
     */
    FACEBOOK,
    /**
     * twitter
     */
    TWITTER,
    /**
     * github
     */
    GITHUB,
    /**
     * google
     */
    GOOGLE,
    /**
     * telegram
     */
    TELEGRAM,
    /**
     * qq
     */
    QQ,
    /**
     * wechat
     */
    WECHAT
    ;

    @Override
    public String asText() {
        return name();
    }
}
