package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.QueryTypeEnum;
/**
 * @author FLJ
 * @date 2022/8/15
 * @time 16:24
 * @Description Application vo 抽象
 */
public interface ApplicationVo extends Vo {
    @Override
    default String getEntityType() {
        return QueryTypeEnum.APPLICATION.name();
    }
}
