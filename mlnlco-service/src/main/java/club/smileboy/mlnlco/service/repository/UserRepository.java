package club.smileboy.mlnlco.service.repository;

import club.smileboy.mlnlco.service.model.entity.AppUserEntity;
import club.smileboy.mlnlco.service.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 14:07
 * @Description user repository
 */
public interface UserRepository extends BaseRepository<AppUserEntity,Integer> , JpaSpecificationExecutor<AppUserEntity> {
}
