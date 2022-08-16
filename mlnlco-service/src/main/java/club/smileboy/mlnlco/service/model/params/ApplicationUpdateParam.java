package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.commons.util.StringUtil;
import club.smileboy.mlnlco.service.model.constant.QueryTypeEnum;
import club.smileboy.mlnlco.service.model.entity.ApplicationEntity;
import club.smileboy.mlnlco.service.model.propertyEnum.IcoType;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

/**
 * @author FLJ
 * @date 2022/8/16
 * @time 15:17
 * @Description application update param
 */
@Data
public class ApplicationUpdateParam implements Param,Query {

    @Override
    public String getEntityType() {
        return QueryTypeEnum.APPLICATION.name();
    }

    /**
     * id
     */
    @NotNull(message = "id must not be null !!!")
    private Integer id;

    /**
     * 更改app 名称
     */
    private String appName;

    /**
     * 主体
     */
    private String sub;

    /**
     * 图标(仅当图标存在的时候,图标类型才有意义)
     */
    private String ico;

    /**
     * 图标类型
     */
    private IcoType icoType;

    @Override
    public void defaultValueValidation() {
        if(appName == null) {
            appName = "";
        }
        if(sub == null) {
            sub = "";
        }

        if(ico == null) {
            ico = "";
        }
        if(icoType == null) {
            if(ico.equals("")) {
                icoType = IcoType.NONE;
            }
            else {
                // 否则根据icoStr 解析
                icoType = IcoType.resolveIcoType(ico);
            }

        }
    }

    /**
     * 是否需要更新
     */
    public boolean checkNeedUpdate() {
        return !StringUtil.INSTANCE.allStringIsEmpty(appName,sub,ico);
    }

    @Override
    public Specification<ApplicationEntity> toSpecification() {
        return new ApplicationSaveParam.AppSaveQuerySpecification(appName,sub);
    }

    @Override
    public boolean checkNeedToSpecification() {
        return true;
    }
}
