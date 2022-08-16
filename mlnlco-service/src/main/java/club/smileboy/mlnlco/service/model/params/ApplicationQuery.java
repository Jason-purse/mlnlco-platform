package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.commons.util.StringUtil;
import club.smileboy.mlnlco.service.model.constant.QueryTypeEnum;
import club.smileboy.mlnlco.service.model.entity.ApplicationEntity;
import club.smileboy.mlnlco.service.model.entity.ApplicationEntity_;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 16:13
 * @Description 应用查询条件
 */
@Getter
public class ApplicationQuery implements Query {

    @Override
    public String getEntityType() {
        return QueryTypeEnum.APPLICATION.name();
    }

    private String appName;

    private String appType;

    private String appSecretType;


    @NotNull
    @Override
    public Specification<ApplicationEntity> toSpecification() {
        return new AppQuerySpecification();
    }

    @Override
    public void defaultValueValidation() {
        if (appName == null) {
            appName = "";
        }
        if(appSecretType == null) {
            appSecretType = "";
        }
        if(appType == null) {
            appType = "";
        }
    }

    @Override
    public boolean checkNeedToSpecification() {
        return StringUtil.INSTANCE.allStringIsEmpty(appName,appType,appSecretType);
    }

    class AppQuerySpecification implements Specification<ApplicationEntity> {
        private static final long serialVersionUID = 1L;

        @Override
        public Predicate toPredicate(Root<ApplicationEntity> root, @NotNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get(ApplicationEntity_.appType), getAppType()),criteriaBuilder.equal(root.get(ApplicationEntity_.appSecretType),getAppSecretType()),
                    criteriaBuilder.like(root.get(ApplicationEntity_.APP_NAME),getAppName()));
        }
    }
}


