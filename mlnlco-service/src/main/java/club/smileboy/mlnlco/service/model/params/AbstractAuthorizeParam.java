package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.AuthorizationType;
import club.smileboy.mlnlco.service.model.propertyEnum.UserOrigin;
import lombok.Setter;

/**
 * @author JASONJ
 * @date 2022/8/17
 * @time 21:53
 * @description 抽象授权参数 实现
 **/
@Setter
public abstract class AbstractAuthorizeParam implements AuthorizeParam {

    private String username;

    private String sub;

    private AuthorizationType authorizationType;

    private UserOrigin origin;

    private Boolean fullPlatFormToken;

    /**
     * app id
     */
    private String appId;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getSub() {
        return sub;
    }

    @Override
    public AuthorizationType getAuthorizeType() {
        return authorizationType;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public UserOrigin getOrigin() {
        return origin;
    }

    @Override
    public Boolean isFullPlatFormToken() {
        return fullPlatFormToken;
    }
}
