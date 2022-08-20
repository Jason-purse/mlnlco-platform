package club.smileboy.mlnlco.service.model.entity;

import club.smileboy.mlnlco.service.model.propertyEnum.OptionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 20:21
 * @description 系统配置属性(动态的配置, 能够在系统中修改,而不需要重启应用配置) ...
 **/
@Entity
@Table(name = "options")
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class OptionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "club.smileboy.mlnlco.service.model.entity.support.CustomIdGenerator")
    private Integer id;

    @Column(name = "opt_key",columnDefinition = "varchar(255)",nullable = false)
    private String optKey;

    @Column(name = "opt_value",columnDefinition = "varchar(50)",nullable = false)
    private String optValue;

    /**
     * 是否为 内部的
     */
    @Column(nullable = false)
    private Boolean internal;

    /**
     * 是否启用
     */
    @Column(nullable = false)
    private Boolean enable;

    @Override
    protected void prePersist() {
        if(internal == null) {
            internal = false;
        }

        if(enable == null) {
            enable = false;
        }
        super.prePersist();
    }
}
