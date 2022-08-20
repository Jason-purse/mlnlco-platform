package club.smileboy.mlnlco.service.model.constant;

import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnum;
/**
 * @author FLJ
 * @date 2022/8/18
 * @time 10:10
 * @Description 常用的属性集
 */
public enum CommonOptions implements ValueEnum<CommonOptions> {

    // jwt 加密类型
    JWT_CRYPT_TYPE("mlnlco.platform.authorize.jwt.crypt.type"),
    JWT_CRYPT("mlnlco.platform.authorize.jwt.crypt"),
    JWS_CRYPT("mlnlco.platform.authorize.jws.crypt"),
    /**
     * 平台 初始化状态
     */
    PLATFORM_INIT_STATUS("mlnlco.platform.init.status"),
    /**
     * jwt 签名 加密类型
     */
    JWT_SIGN_CRYPT_TYPE("mlnlco.platform.authorize.jwt.sign.crypt.type");

    private final String keyName;

    private CommonOptions(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }
}
