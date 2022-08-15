package club.smileboy.mlnlco.service.repository;

import club.smileboy.mlnlco.service.model.entity.ApplicationEntity;
import club.smileboy.mlnlco.service.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 14:21
 * @Description 应用仓库
 */
public interface ApplicationRepository extends BaseRepository<ApplicationEntity,String>  , JpaSpecificationExecutor<ApplicationEntity> {

}
