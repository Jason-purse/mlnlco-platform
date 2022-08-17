package club.smileboy.mlnlco.service.handler;

import club.smileboy.mlnlco.commons.util.ArrayUtil;
import club.smileboy.mlnlco.commons.util.ClassUtil;
import club.smileboy.mlnlco.service.model.constant.QueryTypeEnum;
import club.smileboy.mlnlco.service.model.params.Operation;
import club.smileboy.mlnlco.service.model.params.Query;
import club.smileboy.mlnlco.service.model.params.annotations.QueryType;
import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnum;
import club.smileboy.mlnlco.service.util.RequestUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.PropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 14:35
 * @Description 专注于处理 @QueryType 注解
 */
public class QueryTypeHandleMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Operation.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        QueryType annotation = getParameterAnnotation(parameter);

        if(annotation == null) {
            annotation = getMethodAnnotation(parameter.getMethod());
        }

        if(annotation == null) {
            Assert.notNull(parameter.getMethod(),"method must not be null !!!");
            annotation = getHandlerTypeAnnotation(parameter.getMethod().getDeclaringClass());
        }

        validationAnnotation(annotation);

        Operation operation = BeanUtils.instantiateClass(annotation.targetType());

        // 最后直接返回 ...
        WebDataBinder binder = binderFactory.createBinder(webRequest,operation , Objects.requireNonNull(parameter.getParameterName()));

        // 判断请求方式,如果为 Get,从参数上进行构建,否则从输入流中构建 ...
        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.notNull(nativeRequest,"webRequest not is HttpServletRequest, can't continue process !!!");

        if (RequestUtil.INSTANCE.isGetRequest(nativeRequest)) {
            // get 请求不管 请求体 ..
            PropertyValues propertyValues = RequestUtil.INSTANCE.convertPropertyValues(nativeRequest, false, null);
            binder.bind(propertyValues);
        }
        // post request body or params
        else {
            // 在非Get请求中,(在Get请求中这个参数将被忽略)如果注解主动告诉我们 它从body里面获取 ,我们就不用解析了请求体 ..
            PropertyValues propertyValues = RequestUtil.INSTANCE.convertPropertyValues(nativeRequest, annotation.fromBody(), MediaType.APPLICATION_JSON);
            binder.bind(propertyValues);
        }

        // validation

        Class<?>[] classes = annotation.validationHints();
        if(ArrayUtil.INSTANCE.isEmpty(classes)) {
            binder.validate();
        }
        else {
            // 最后可以执行校验
            binder.validate((Object[]) classes);
        }


        BindingResult bindingResult = binder.getBindingResult();
        StringBuilder builder = new StringBuilder();
        if (bindingResult.getFieldErrorCount() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                builder.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("\n");
            }
            // 抛出异常
            throw new ConstraintViolationException(builder.toString(),null);
        }

        // 校验默认值设置
        operation.defaultValueValidation();
        // 返回结果
        return binder.getTarget();
    }

    private void validationAnnotation(QueryType annotation) {
        Assert.notNull(annotation, "Query Type class must annotated by @QueryType !!!");
        Assert.hasText(annotation.value(), "@QueryType value attribute must not be null !!!");
        Assert.notNull(annotation.targetType(),"@QueryType targetType attribute must not be null !!!");
        if (ValueEnum.of(QueryTypeEnum.class, annotation.value().trim())
                .isPresent()) {
            // 如果存在,则判断是否为抽象类,如果为抽象类报错
            ClassUtil.INSTANCE.ifAbstract(annotation.targetType(),() -> {
                // 如果为抽象类 报错
                throw new IllegalArgumentException("@QueryType targetType attribute must not be abstract !!!");
            });

            ClassUtil.INSTANCE.ifNoDefaultConstructor(annotation.targetType(),() -> {
                throw new IllegalArgumentException("@QueryType targetType class must have default constructor !!!");
            });
        }
    }

    private QueryType getHandlerTypeAnnotation(Class<?> declaringClass) {
        return AnnotationUtils.getAnnotation(declaringClass,QueryType.class);
    }

    private QueryType getMethodAnnotation(Method method) {
        return AnnotationUtils.getAnnotation(method,QueryType.class);
    }

    @Nullable
    private QueryType getParameterAnnotation(MethodParameter parameter) {
        return parameter.getParameterAnnotation(QueryType.class);
    }
}
