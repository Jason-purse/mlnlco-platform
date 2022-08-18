package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.QueryTypeEnum;
import club.smileboy.mlnlco.service.model.property.Email;
import club.smileboy.mlnlco.service.model.property.Phone;
import club.smileboy.mlnlco.service.model.propertyEnum.SexType;
import club.smileboy.mlnlco.service.model.propertyEnum.UserOrigin;
import lombok.Data;

import java.util.Date;

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 15:34
 * @Description 用户详情Vo
 */
@Data
public class AppUserDetailVo implements AppUserVo {
    @Override
    public String getEntityType() {
        return QueryTypeEnum.USER.name();
    }

    private Integer id;


    private String username;


    /**
     * 密码可空,当它来自 其他社交平台时 ...
     */
    private String password;


    private SexType sex;


    private Date birthday;


    private UserOrigin originType;

    /**
     * 邮箱
     */
    private Email email;

    /**
     * 电话
     */
    private Phone phone;

    /**
     * 昵称
     */
    private String nickname;


}
