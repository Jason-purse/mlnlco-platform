package club.smileboy.mlnlco.service.model.constant;

import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnum;
/**
 * @author FLJ
 * @date 2022/8/17
 * @time 13:41
 * @Description 授权类型(或者说登录类型)
 *
 * 通过 jwt 令牌 传递给我们的认证端点 ...
 *
 * 目前通过 非对称加密加密 /解密 jwt 字符串
 */
public enum AuthorizationType implements ValueEnum<AuthorizationType> {

    /**
     * oauth2
     *
     * 直接将用户信息传递给我们 ...
     *
     * username
     * email
     * nickname ...
     * user-origin
     *
     */
    OAUTH2,

    /**
     * user_password
     *
     * username
     * password
     * 直接将用户信息传递给我们 ...
     */
    USER_PASSWORD;


}
