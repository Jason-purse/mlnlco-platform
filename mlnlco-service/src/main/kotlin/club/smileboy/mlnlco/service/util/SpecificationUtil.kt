package club.smileboy.mlnlco.service.util

import club.smileboy.mlnlco.service.model.entity.BaseEntity
import javax.persistence.metamodel.SingularAttribute

/**
 * @author FLJ
 * @date 2022/8/16
 * @time 9:44
 * @Description specification util
 */
object SpecificationUtil {

    fun <Entity: BaseEntity,P> createSingularAttribute(entityClass: Class<Entity>,directPropertyType: Class<P>,propertyName: String): SingularAttribute<Entity,P>? {
        return null;
    }
}