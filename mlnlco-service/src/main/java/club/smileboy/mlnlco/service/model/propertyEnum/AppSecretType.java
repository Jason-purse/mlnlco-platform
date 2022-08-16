package club.smileboy.mlnlco.service.model.propertyEnum;

import club.smileboy.mlnlco.commons.util.EncryptUtil;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 12:45
 * @Description App Secret type
 */
public enum AppSecretType implements PropertyEnum<AppSecretType> {
    RSA {
        @Override
        public AppSecret generateAppSecret() {
            throw new UnsupportedOperationException("current time is not support this secret type !!!");
        }

        @Override
        public AppSecret generateAppSecret(String secretStr) {
            throw new UnsupportedOperationException("current time is not support this secret type !!!");
        }
    },
    NORMAL_SECRET {
        @Override
        public AppSecret generateAppSecret() {
            return AppSecret.Companion.of(this,EncryptUtil.INSTANCE.generateNormalEncryptStringsWithBase64()).orElseThrow(() ->  new IllegalArgumentException("can't generate appropriate appSecret type !!!"));
        }

        @Override
        public AppSecret generateAppSecret(String secretStr) {
            return AppSecret.Companion.of(this,EncryptUtil.INSTANCE.generateNormalEncryptStringsWithBase64(secretStr)).orElseThrow(() -> new IllegalArgumentException("can't generate appropriate appSecret type !!!"));
        }
    }
    ;

    @Override
    public String asText() {
        return name();
    }


    public abstract AppSecret generateAppSecret();


    public abstract AppSecret generateAppSecret(String secretStr);

    /**
     * 它可能是一个加密对 / 或者普通加密串
     * @param appSecretType appSecretType
     * @return appSecret
     */
    public static AppSecret generateEncryptString(AppSecretType appSecretType) {
        return appSecretType.generateAppSecret();
    }

    /**
     * 加密对 / 普通加密串
     * @param appSecretType app secret type
     * @param secretStr 盐
     * @return appSecret
     */
    public static AppSecret generateEncryptString(AppSecretType appSecretType,String secretStr) {
        return appSecretType.generateAppSecret(secretStr);
    }

}
