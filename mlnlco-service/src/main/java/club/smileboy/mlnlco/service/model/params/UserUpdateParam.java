package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.QueryTypeEnum;
import club.smileboy.mlnlco.service.model.entity.AppUserEntity;
import club.smileboy.mlnlco.service.model.property.Phone;
import club.smileboy.mlnlco.service.model.propertyEnum.SexType;
import club.smileboy.mlnlco.service.model.propertyEnum.UserOrigin;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 16:29
 * @Description 用户更新参数
 */
@Data
public class UserUpdateParam implements Param ,Query{
    @Override
    public String getEntityType() {
        return QueryTypeEnum.USER.name();
    }


    @NotNull(message = "id must not be null !!!")
    private Integer id;

    @NotBlank(message = "username must not be null !!!")
    private String username;

    private String password;

    private SexType sex;

    private Date birthday;

    @NotNull(message = "origin must not be null !!!")
    private UserOrigin origin;

    /**
     * 邮箱
     */
    @Email(message = "mailbox format is incorrect   !!!")
    private String email;

    /**
     * 电话
     */
    private Phone phone;

    /**
     * 昵称
     */
    private String nickname;

    @NonNull
    @Override
    public Specification<AppUserEntity> toSpecification() {
        return new UserSaveParam.UserUniqueQuerySpecification(username,origin);
    }

    @Override
    public boolean checkNeedToSpecification() {
        return username != null && origin != null;
    }

    @Override
    public void defaultValueValidation() {

        if(email == null) {
            email = "";
        }

        if(phone != null) {
            phone.validationValidity();
        }
        else {
            phone = Phone.EMPTY;
        }

        if(password == null) {
            password = "";
        }
    }
}
