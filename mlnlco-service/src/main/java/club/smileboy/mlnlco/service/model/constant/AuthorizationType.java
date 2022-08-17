package club.smileboy.mlnlco.service.model.constant;

import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnum;
/**
 * @author FLJ
 * @date 2022/8/17
 * @time 13:41
 * @Description 授权类型(或者说登录类型)
 */
public enum AuthorizationType implements ValueEnum<AuthorizationType> {

    /**
     * oauth2
     */
    OAUTH2,

    /**
     * user_password
     */
    USER_PASSWORD,

    /**
     * jwt
     */
    JWT
    ;


}
