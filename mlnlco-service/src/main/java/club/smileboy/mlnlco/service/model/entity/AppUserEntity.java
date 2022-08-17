package club.smileboy.mlnlco.service.model.entity;

import club.smileboy.mlnlco.service.model.propertyEnum.SexType;
import club.smileboy.mlnlco.service.model.propertyEnum.UserOrigin;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 13:31
 * @Description 应用用户信息
 *
 * 同一管理的用户信息
 *
 *
 * 考虑到 originType 和用户之间的关系
 * 例如一个用户可能去多个社交平台注册,那么我们根据社交平台来管理用户的信息 ...
 *
 * 例如有些社交平台,根本没有用户的真实信息 ...
 *
 * 这样在建立搜索时,是比较简单的 ...(但是会存在较多的冗余数据,如果多个平台的信息大致一样的话) ..
 *
 * 综合考虑,使用这种方式 ...
 */
@Entity
@Table(name = "app_user",indexes = {
        @Index(name = "username_unique_Key",unique = true,columnList = "username,originType")
})
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class AppUserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "club.smileboy.mlnlco.service.model.entity.support.CustomIdGenerator")
    private Integer id;

    @Column(nullable = false)
    private String username;


    /**
     * 密码可空,当它来自 其他社交平台时 ...
     */
    private String password;


    @Column(nullable = false,columnDefinition = "varchar(20)")
    @ColumnDefault("MAN")
    private SexType sex;


    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    /**
     * 来源类型
     */
    @Column(name = "origin_type",columnDefinition = "varchar(25)")
    @ColumnDefault("NATIVE_REGISTER")
    private UserOrigin originType;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 昵称
     */
    @ColumnDefault("'anonymous_user'")
    private String nickname;

    @Override
    protected void prePersist() {
        if(email == null) {
            email = "";
        }
        if(phone == null) {
            phone = "";
        }
        if(nickname == null) {
            nickname = "anonymous_user";
        }
    }
}
