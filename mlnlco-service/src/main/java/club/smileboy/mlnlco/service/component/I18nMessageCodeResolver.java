package club.smileboy.mlnlco.service.component;

import club.smileboy.mlnlco.service.exception.SystemException;
import club.smileboy.mlnlco.service.model.constant.I18nMessageResolveType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Locale;
import java.util.Optional;

/**
 * @author FLJ
 * @date 2022/8/16
 * @time 17:06
 * @Description i18n 国际化消息代码 解析器 ...
 */
public interface I18nMessageCodeResolver {

    /**
     * 根据异常code解析消息
     * @param code code
     * @return 国际化消息
     */
    @NonNull
    default String resolveMessageForException(int code, Locale locale,@Nullable Object[] args) throws IllegalAccessException {
        return resolveMessage(I18nMessageResolveType.EXCEPTION.getPrefix(),code,locale,args)
                .orElseThrow(() -> new SystemException("message code can't resolve !!!"));
    }

    /**
     * 同上
     */
    @NonNull
    default String resolveMessageForException(int code, Locale locale) throws IllegalAccessException {
        return resolveMessageForException(code,locale,(Object[]) null);
    }

    /**
     * 同上
     * @param defaultMessage 提供默认值
     */
    @NonNull
    default String resolveMessageForException(int code, Locale locale,@NonNull String  defaultMessage,@Nullable  Object[] args) throws IllegalAccessException {
        return resolveMessage(I18nMessageResolveType.EXCEPTION.getPrefix(),code,locale,args).orElse(defaultMessage);
    }

    /**
     * 同上
     */
    @NonNull
    default String resolveMessageForException(int code, Locale locale,@NonNull String  defaultMessage) throws IllegalAccessException {
        return resolveMessageForException(code,locale,defaultMessage,null);
    }

    /**
     * 同上,但是不一定存在message
     */
    default Optional<String> resolveOptionalMessageForException(int code, Locale locale,@Nullable  Object[] args) throws IllegalAccessException {
        return resolveMessage(I18nMessageResolveType.EXCEPTION.getPrefix(), code,locale,args);
    }

    /**
     * 同上
     */
    default Optional<String> resolveOptionalMessageForException(int code, Locale locale) throws IllegalAccessException {
        return resolveOptionalMessageForException(code,locale,(Object[]) null);
    }

    /**
     * 根据验证code解析消息
     * @param code code
     * @return 国际化消息
     */
    @NonNull
    default String resolveMessageForValidation(int code, Locale locale,@Nullable  Object[] args) throws IllegalAccessException {
        return resolveMessage(I18nMessageResolveType.VALIDATION.getPrefix(), code,locale,args).orElseThrow(() -> new SystemException("message code can't resolve !!!"));
    }

    @NonNull
    default String resolveMessageForValidation(int code, Locale locale) throws IllegalAccessException {
        return resolveMessageForValidation(code,locale,(Object[])null);
    }


    /**
     * 同上
     * @param defaultMessage 默认消息值 ..
     */
    @NonNull
    default String resolveMessageForValidation(int code, Locale locale,@NonNull String defaultMessage,@Nullable  Object[] args) throws IllegalAccessException {
        return resolveMessage(I18nMessageResolveType.VALIDATION.getPrefix(), code,locale,args).orElse(defaultMessage);
    }

    @NonNull
    default String resolveMessageForValidation(int code, Locale locale,@NonNull String defaultMessage) throws IllegalAccessException {
        return resolveMessageForValidation(code,locale,defaultMessage,null);
    }

    /**
     * 同上,但是不一定存在 message
     * @param code code
     */
    default Optional<String> resolveOptionalMessageForValidation(int code, Locale locale,@Nullable  Object[] args) throws IllegalAccessException {
        return resolveMessage(I18nMessageResolveType.VALIDATION.getPrefix(), code,locale,args);
    }

    default Optional<String> resolveOptionalMessageForValidation(int code, Locale locale) throws IllegalAccessException {
        return resolveOptionalMessageForValidation(code,locale,null);
    }


    /**
     * @param messagePrefix 消息前缀
     * @param code code
     * @return 国际化消息
     * @implNote 需要正确返回是否具有对应的消息,对于不存在的消息前缀,可以采取自定义策略返回 ...
     * @exception IllegalAccessException 如果底层资源还没有初始化好可以返回无效访问异常 ... / 自定义行为 ...
     */
    Optional<String> resolveMessage(@NonNull String messagePrefix,int code, Locale locale,@Nullable  Object[] args) throws IllegalAccessException;


}
