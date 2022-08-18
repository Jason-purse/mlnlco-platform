package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.AuthorizationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JASONJ
 * @date 2022/8/17
 * @time 21:36
 * @description username / password authorize
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPasswordAuthorizeParam extends AbstractAuthorizeParam {

    public UserPasswordAuthorizeParam() {
        setAuthorizationType(AuthorizationType.USER_PASSWORD);
    }

    @Override
    public String getEntityType() {
        throw new UnsupportedOperationException();
    }

    private String password;
}
