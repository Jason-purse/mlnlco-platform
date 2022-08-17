package club.smileboy.mlnlco.service.model.entity;

import club.smileboy.mlnlco.service.model.propertyEnum.OptionType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 20:21
 * @description 系统配置属性(动态的配置, 能够在系统中修改,而不需要重启应用配置) ...
 **/
@Table(name = "options")
public class OptionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "club.smileboy.mlnlco.service.model.entity.support.CustomIdGenerator")
    private Integer id;

    @Column(name = "opt_key")
    private String optKey;

    @Column(name = "opt_value",columnDefinition = "varchar(50)")
    private OptionType optValue;
}
