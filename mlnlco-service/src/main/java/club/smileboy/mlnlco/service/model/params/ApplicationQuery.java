package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.QueryTypeEnum;
import club.smileboy.mlnlco.service.model.entity.ApplicationEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.criteria.*;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 16:13
 * @Description 应用查询条件
 */
@Data
public class ApplicationQuery implements Query {

    @Override
    public String getEntityType() {
        return QueryTypeEnum.APPLICATION.name();
    }


    private String appName;

    private String appType;

    private String appSecretType;


    @Override
    public Specification<ApplicationEntity> convert() {
        return null;
    }
}

class AppQuerySpecification implements Specification<ApplicationEntity> {
    private static final long serialVersionUID = 1L;
    private final JpaEntityInformation<ApplicationEntity, String> entityInformation;

    public AppQuerySpecification(JpaEntityInformation<ApplicationEntity,String> entityInformation) {
        this.entityInformation = entityInformation;
    }
    @Override
    public Predicate toPredicate(Root<ApplicationEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class);

        return null;
    }
}
