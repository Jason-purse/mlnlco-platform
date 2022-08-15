package club.smileboy.mlnlco.service.model.entity.support;

import club.smileboy.mlnlco.commons.util.ReflectionUtil;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;
/**
 * @author FLJ
 * @date 2022/8/15
 * @time 13:51
 * @Description 自定义ID 生成器 ...
 */
public class CustomIdGenerator extends IdentityGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        Object id = ReflectionUtil.INSTANCE.getFieldValue("id",object);
        if (id != null) {
            return (Serializable) id;
        }
        return super.generate(session, object);
    }
}