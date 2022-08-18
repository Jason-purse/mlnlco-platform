package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.AuthorizationType;

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
    String getUserName();

    /**
     * 请求令牌主站
     */
    String getSub();

    /**
     * 获取授权类型
     */
    AuthorizationType getAuthorizeType();
}
