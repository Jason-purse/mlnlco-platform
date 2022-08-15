package club.smileboy.mlnlco.service.model.constant;

import club.smileboy.mlnlco.service.model.entity.ApplicationEntity;
import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnum;
/**
 * @author FLJ
 * @date 2022/8/15
 * @time 14:33
 * @Description 查询类型
 */
public enum QueryTypeEnum implements ValueEnum<QueryTypeEnum> {
    APPLICATION(ApplicationEntity.class)
    ;

    private final Class<?> queryType;
    private QueryTypeEnum(Class<?> queryType) {
        this.queryType = queryType;
    }

    public Class<?> getQueryType() {
        return queryType;
    }
}
