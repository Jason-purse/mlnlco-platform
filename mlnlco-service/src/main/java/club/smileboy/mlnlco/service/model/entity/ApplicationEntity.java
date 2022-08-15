package club.smileboy.mlnlco.service.model.entity;

import club.smileboy.mlnlco.service.model.propertyEnum.AppSecret;
import club.smileboy.mlnlco.service.model.propertyEnum.AppSecretType;
import club.smileboy.mlnlco.service.model.propertyEnum.ApplicationType;
import club.smileboy.mlnlco.service.model.propertyEnum.IcoType;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 11:44
 * @Description application entity
 */
@Entity
@Table(name = "application-info")
@ToString
@EqualsAndHashCode(callSuper = true)
public class ApplicationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "club.smileboy.mlnlco.service.model.entity.support"
            + ".CustomIdGenerator")
    private String id;

    /**
     * app id
     */
    private String appId;

    /**
     * app type
     */
    @Column(name = "app-type")
    private ApplicationType appType;

    /**
     * 主体信息
     */
    private String sub;

    /**
     * 应用名称
     */
    @Column(name = "app-name")
    private String appName;

    /**
     * 图标
     */
    @ColumnDefault("")
    private String ico;

    /**
     * 图标类型
     */
    @Column(name = "ico-type")
    @ColumnDefault(value = "NONE")
    private IcoType icoType;

    /**
     * 应用密钥
     */
    @Column(name = "app-secret")
    @Convert(converter = AppSecret.Companion.AppSecretAttributeConverter.class)
    private AppSecret appSecret;

    /**
     * 应用密钥类型
     */
    @Column(name = "app-secret-type")
    private AppSecretType appSecretType;

    @Override
    protected void prePersist() {
        if(ico == null) {
            ico = "";
        }
        if(icoType == null) {
            icoType = IcoType.NONE;
        }
        super.prePersist();
    }
}
