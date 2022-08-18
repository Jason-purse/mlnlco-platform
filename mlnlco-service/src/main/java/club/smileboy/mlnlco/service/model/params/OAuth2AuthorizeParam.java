package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.AuthorizationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JASONJ
 * @date 2022/8/17
 * @time 22:01
 * @description oauth2 授权参数
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OAuth2AuthorizeParam extends AbstractAuthorizeParam {

    public OAuth2AuthorizeParam() {
        setAuthorizationType(AuthorizationType.OAUTH2);
    }

    @Override
    public String getEntityType() {
        throw new UnsupportedOperationException();
    }

}
