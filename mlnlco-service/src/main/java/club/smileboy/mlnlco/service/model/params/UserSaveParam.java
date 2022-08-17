package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.QueryTypeEnum;
import club.smileboy.mlnlco.service.model.entity.AppUserEntity;
import club.smileboy.mlnlco.service.model.entity.AppUserEntity_;
import club.smileboy.mlnlco.service.model.property.Phone;
import club.smileboy.mlnlco.service.model.propertyEnum.SexType;
import club.smileboy.mlnlco.service.model.propertyEnum.UserOrigin;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 16:39
 * @Description 用户保存参数 ..
 */
@Data
public class UserSaveParam implements Param, Query {


    @Override
    public String getEntityType() {
        return QueryTypeEnum.USER.name();
    }


    @NotBlank(message = "username must not be null !!!")
    private String username;


    private String password;

    @NotBlank(message = "sex must not be null !!!")
    private SexType sex;


    private Date birthday;

    /**
     * 来源类型
     */
    private UserOrigin originType;

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

    @Override
    public void defaultValueValidation() {
        if (originType == null) {
            // 仅当 username / password 传递了,否则 不允许保存 ...
            if (password != null) {
                originType = UserOrigin.NATIVE_REGISTER;
            }

            if(password == null) {
                throw new IllegalArgumentException("origin type must not be null !!!");
            }
        }

        if(password == null) {
            password = "";
        }

        if (phone != null) {
            // 邮箱格式校验
            phone.validationValidity();
        }
        else {
            phone = Phone.EMPTY;
        }

        if (nickname == null) {
            nickname = "anonymous_user";
        }
    }

    @Override
    public Specification<AppUserEntity> toSpecification() {
        // 用户名 和 originType 必须唯一
        return new UserUniqueQuerySpecification(username,originType);
    }

    @Override
    public boolean checkNeedToSpecification() {
        return true;
    }

    /**
     * 用户信息查询(保证 originType 和 userName 必须组合唯一)
     */
    public static class UserUniqueQuerySpecification implements Specification<AppUserEntity> {

        private final String username;
        private final UserOrigin origin;
        public UserUniqueQuerySpecification(String username,UserOrigin origin) {
            this.username = username;
            this.origin = origin;
        }
        @Override
        public Predicate toPredicate(Root<AppUserEntity> root, @NotNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            return criteriaBuilder.and(criteriaBuilder.equal(root.get(AppUserEntity_.username), username),
                    criteriaBuilder.equal(root.get(AppUserEntity_.originType), origin.asText()));
        }
    }
}
