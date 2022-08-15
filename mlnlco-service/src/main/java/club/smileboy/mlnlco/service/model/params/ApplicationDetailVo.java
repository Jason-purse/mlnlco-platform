package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.propertyEnum.AppSecret;
import club.smileboy.mlnlco.service.model.propertyEnum.AppSecretType;
import club.smileboy.mlnlco.service.model.propertyEnum.ApplicationType;
import club.smileboy.mlnlco.service.model.propertyEnum.IcoType;
import lombok.Data;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 16:55
 * @Description application detail vo
 */
@Data
public class ApplicationDetailVo implements ApplicationVo {

    private String id;

    /**
     * app id
     */
    private String appId;

    /**
     * app type
     */
    private ApplicationType appType;

    /**
     * 主体信息
     */
    private String sub;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 图标
     */
    private String ico;

    /**
     * 图标类型
     */
    private IcoType icoType;

    /**
     * 应用密钥
     */
    private AppSecret appSecret;

    /**
     * 应用密钥类型
     */
    private AppSecretType appSecretType;

}
