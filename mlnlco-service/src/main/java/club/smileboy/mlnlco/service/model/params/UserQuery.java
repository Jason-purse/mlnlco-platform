package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.QueryTypeEnum;
import club.smileboy.mlnlco.service.model.entity.AppUserEntity;
import club.smileboy.mlnlco.service.model.entity.AppUserEntity_;
import club.smileboy.mlnlco.service.model.propertyEnum.SexType;
import club.smileboy.mlnlco.service.model.propertyEnum.UserOrigin;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 14:13
 * @Description 用户查询
 */
@Data
public class UserQuery implements Query {

    private SexType sex;

    /**
     * 开始时间,结束时间
     */
    private Date birthDayStartTime;

    private Date birthDayEndTime;

    /**
     * 用户来源 ...
     */
    private UserOrigin origin;


    @Override
    public String getEntityType() {
        return QueryTypeEnum.USER.name();
    }

    @Override
    public Specification<AppUserEntity> toSpecification() {
        return new UserQuerySpecification();
    }




    @Override
    public boolean checkNeedToSpecification() {
        return sex != null || (birthDayStartTime != null || birthDayEndTime != null) || origin != null;
    }



    class UserQuerySpecification implements Specification<AppUserEntity> {

        @Override
        public Predicate toPredicate(Root<AppUserEntity> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
            Predicate expression = null;
            Path<Date> birthDay = root.get(AppUserEntity_.birthday);
            if(birthDayStartTime != null && birthDayEndTime != null) {
                expression = criteriaBuilder.between(birthDay, birthDayStartTime, birthDayEndTime);
            }
            else if(birthDayStartTime != null) {
                expression = criteriaBuilder.greaterThanOrEqualTo(birthDay,birthDayStartTime);
            }
            else {
                expression = criteriaBuilder.lessThanOrEqualTo(birthDay,birthDayEndTime);
            }

            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get(AppUserEntity_.originType),getOrigin().asText()),
                    criteriaBuilder.equal(root.get(AppUserEntity_.sex),getSex().asText()),
                    expression
            );
        }
    }
}
