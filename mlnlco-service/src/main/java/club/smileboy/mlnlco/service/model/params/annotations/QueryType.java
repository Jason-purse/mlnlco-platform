package club.smileboy.mlnlco.service.model.params.annotations;

import club.smileboy.mlnlco.service.model.params.Query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 14:29
 * @Description 查询的类型解释
 *
 * controller class / method / parameter 都可以注解 ..
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryType {
    /**
     * 查询资源类型
     */
    String value();

    /**
     * 目标类型
     */
    Class<? extends Query> targetType();

    /**
     * 是否从request body上获取
     * @return 默认false
     */
    boolean fromBody() default false;

    /**
     * 校验 Hints
     * @return hints array
     */
    Class<?>[] validationHints() default {};
}
