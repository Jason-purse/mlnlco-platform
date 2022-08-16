package club.smileboy.mlnlco.service.model.entity;

import club.smileboy.mlnlco.service.model.propertyEnum.AppSecret;
import club.smileboy.mlnlco.service.model.propertyEnum.AppSecretType;
import club.smileboy.mlnlco.service.model.propertyEnum.ApplicationType;
import club.smileboy.mlnlco.service.model.propertyEnum.IcoType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 11:44
 * @Description application entity
 */
@Entity
@Table(name = "applicationInfo",indexes = {
        @Index(unique = true,name = "application_info_appid_unique_key",columnList = "appId"),
})
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "club.smileboy.mlnlco.service.model.entity.support.CustomIdGenerator")
    private Integer id;

    /**
     * app id
     */
    private String appId;

    /**
     * app type
     */
    @Column(name = "app_type",columnDefinition = "varchar(25)")
    private ApplicationType appType;

    /**
     * 主体信息
     */
    private String sub;

    /**
     * 应用名称
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * 图标
     */
    @ColumnDefault("''")
    private String ico;

    /**
     * 图标类型
     */
    @Column(name = "ico_type",columnDefinition = "varchar(25)")
    @ColumnDefault(value = "'NONE'")
    private IcoType icoType;

    /**
     * 应用密钥
     */
    @Column(name = "app_secret",columnDefinition = "varchar(255)")
    @Convert(converter = AppSecret.Companion.AppSecretAttributeConverter.class)
    private AppSecret appSecret;

    /**
     * 应用密钥类型
     */
    @Column(name = "app_secret_type",columnDefinition = "varchar(25)")
    private AppSecretType appSecretType;

    @Override
    protected void prePersist() {
        if(ico == null) {
            ico = "";
        }
        if(icoType == null) {
            if(ico.equals("")) {
                icoType = IcoType.NONE;
            }
        }
        super.prePersist();
    }
}
