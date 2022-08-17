package club.smileboy.mlnlco.service.model.params;

import club.smileboy.mlnlco.service.model.constant.QueryTypeEnum;
/**
 * @author FLJ
 * @date 2022/8/17
 * @time 15:34
 * @Description 用户详情Vo
 */
public class AppUserDetailVo implements AppUserVo {
    @Override
    public String getEntityType() {
        return QueryTypeEnum.USER.name();
    }
}
