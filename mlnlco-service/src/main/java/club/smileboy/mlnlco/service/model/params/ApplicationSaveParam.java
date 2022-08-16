package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.QueryTypeEnum;
import club.smileboy.mlnlco.service.model.entity.ApplicationEntity;
import club.smileboy.mlnlco.service.model.entity.ApplicationEntity_;
import club.smileboy.mlnlco.service.model.propertyEnum.AppSecretType;
import club.smileboy.mlnlco.service.model.propertyEnum.ApplicationType;
import club.smileboy.mlnlco.service.model.propertyEnum.IcoType;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

/**
 * @author FLJ
 * @date 2022/8/16
 * @time 12:51
 * @Description application save param
 */
@Data
public class ApplicationSaveParam implements Param, Query {
    @Override
    public String getEntityType() {
        return QueryTypeEnum.APPLICATION.name();
    }


    /**
     * app type
     */
    @NotNull(message = "appType must not be null !!!")
    private ApplicationType appType;

    /**
     * 主体信息
     */
    @NotNull(message = "sub must not be null !!!")
    private String sub;

    /**
     * 应用名称
     */
    @NotNull(message = "appName must not be null !!!")
    private String appName;

    /**
     * 图标
     */
    private String ico;

    /**
     * 图标类型
     */
    private IcoType icoType;

    /**
     * 应用密钥类型
     */
    @NotNull(message = "appSecretType must not be null !!!")
    private AppSecretType appSecretType;

    @Override
    public void defaultValueValidation() {
        if (ico == null) {
            ico = "";
        }
        if (icoType == null) {
            if (ico.equals("")) {
                icoType = IcoType.NONE;
            } else {
                // 否则根据icoStr 解析
                icoType = IcoType.resolveIcoType(ico);
            }
        }
    }


    @Override
    @NonNull
    public Specification<ApplicationEntity> toSpecification() {
        return new AppSaveQuerySpecification(appName,sub);
    }

    @Override
    public boolean checkNeedToSpecification() {
        return true;
    }

    /**
     * app SaveQuerySpecification ...
     */
    public static
    class AppSaveQuerySpecification implements Specification<ApplicationEntity> {
        private static final long serialVersionUID = 1L;
        private final String appName;
        private final String sub;

        public AppSaveQuerySpecification(String appName, String sub) {
            this.appName = appName;
            this.sub = sub;
        }

        @Override
        public Predicate toPredicate(Root<ApplicationEntity> root, @NonNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            return criteriaBuilder.or(criteriaBuilder.equal(root.get(ApplicationEntity_.appName), appName.trim()),
                    criteriaBuilder.equal(root.get(ApplicationEntity_.sub), sub));
        }
    }
}



