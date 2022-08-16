package club.smileboy.mlnlco.service.model.params;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 16:54
 * @Description 顶层操作抽象
 */
public interface Operation {
    /**
     * 获取需要操作的 信息类型
     * @return
     */
    public String getEntityType();

    /**
     * 默认值校验
     * 例如 vo 不需要校验
     *
     * @see club.smileboy.mlnlco.service.handler.QueryTypeHandleMethodArgumentResolver#resolveArgument(MethodParameter, ModelAndViewContainer, NativeWebRequest, WebDataBinderFactory)
     */
    default void defaultValueValidation() {
        // 默认是不需要的 ...
    }
}
