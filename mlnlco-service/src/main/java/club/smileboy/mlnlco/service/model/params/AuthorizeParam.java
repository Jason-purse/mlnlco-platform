package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.AuthorizationType;
import club.smileboy.mlnlco.service.model.propertyEnum.UserOrigin;

/**
 * @author JASONJ
 * @date 2022/8/17
 * @time 21:35
 * @description 授权参数
 **/
public interface AuthorizeParam extends Param {
    /**
     * 需要产生 jwt 令牌的账户 ...
     */
    String getUsername();

    /**
     * 请求令牌主站
     */
    String getSub();

    /**
     * 获取授权类型
     */
    AuthorizationType getAuthorizeType();


    String getAppId();

    /**
     * 用户来源
     */
    UserOrigin getOrigin();

    /**
     * 全平台所有应用通用Token ..
     * (根据用户的注册情况,颁发符合特定范围的 Token) ....
     * @return false / true
     */
    Boolean isFullPlatFormToken();
}
