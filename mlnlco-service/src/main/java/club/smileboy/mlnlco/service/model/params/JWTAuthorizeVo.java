package club.smileboy.mlnlco.service.model.params;

import lombok.Data;
import lombok.Setter;

/**
 * @author JASONJ
 * @date 2022/8/17
 * @time 22:05
 * @description jwt 授权vo
 **/
@Setter
public class JWTAuthorizeVo implements AuthorizeVo {
    @Override
    public String getEntityType() {
        throw new UnsupportedOperationException();
    }

    private String token;

    public String getToken() {
        return token;
    }
}
