package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.AuthorizationType;
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

    @Override
    public String getUserName() {
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
}
