package club.smileboy.mlnlco.service.advice;

import club.smileboy.mlnlco.service.component.I18nMessageService;
import club.smileboy.mlnlco.service.exception.AbstractI18nException;
import club.smileboy.mlnlco.service.exception.AppGenericI18nException;
import club.smileboy.mlnlco.service.exception.SystemException;
import club.smileboy.mlnlco.service.model.constant.ExceptionValues;
import club.smileboy.mlnlco.service.util.AppResult;
import club.smileboy.mlnlco.service.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author FLJ
 * @date 2022/8/16
 * @time 16:59
 * @Description 基于 Controller 错误消息拦截的Advice ..
 */
@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "club.smileboy.mlnlco.service.controller")
public class AppControllerAdvice {

    private final I18nMessageService messageService;


    /**
     * 最大模糊处理异常
     */
    @ExceptionHandler(SystemException.class)
    public AppResult<?> handleSystemException(SystemException systemException) {
        if (systemException.getCause() != null) {
            systemException.getCause().printStackTrace();
        } else {
            systemException.printStackTrace();
        }
        return AppResult.Companion.error(ExceptionValues.SERVER_INTERNAL_ERROR.getCode(), ExceptionValues.SERVER_INTERNAL_ERROR.name());
    }

    /**
     * 处理无效参数异常 ...
     *
     * @param e 无效参数异常
     * @return 错误消息
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public AppResult<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return AppResult.Companion.error(ExceptionValues.BAD_REQUEST.getCode(), e.getMessage());
    }

    /**
     * 直接处理 参数验证异常
     *
     * @param constraintViolationException 参数验证异常
     * @return 响应结果
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public AppResult<?> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        return AppResult.Companion.error(ExceptionValues.BAD_REQUEST.getCode(), constraintViolationException.getMessage());
    }


    /**
     * 处理不支持操作异常
     *
     * @param e e
     * @return 响应结果
     */
    @ExceptionHandler(UnsupportedOperationException.class)
    public AppResult<?> handleUnSupportedOperationException(UnsupportedOperationException e) {
        e.printStackTrace();
        return handleSystemException(new SystemException(e));
    }

    /**
     * 处理运行时异常
     *
     * @param e e
     * @return 返回消息结果
     */
    @ExceptionHandler(RuntimeException.class)
    public AppResult<?> handleRuntimeException(RuntimeException e) {
        return handleSystemException(new SystemException(e));
    }

    /**
     * 国际化处理 ...
     * 跟业务相关的 ...
     *
     * @param e 无法操作的异常
     * @return 错误消息 ..
     */
    @ExceptionHandler(AbstractI18nException.class)
    public AppResult<?> handleAbstractI18nException(AbstractI18nException e) {
        e.printStackTrace();
        try {

            if (e.isNeededI18n()) {
                return AppResult.Companion.error(ExceptionValues.BAD_REQUEST.getCode(), messageService.resolveMessageForValidation(
                        e.getCode(), RequestUtil.INSTANCE.getRequestLocale(), e.getDefaultMessage(),
                        e instanceof AppGenericI18nException ? ((AppGenericI18nException) e).getArgs() : null
                ));
            }

        } catch (Exception ee) {
            throw new SystemException(ee.getMessage());
        }

        // 否则直接给出错误请求(消息提示就是 异常弹出来的) ...
//        return AppResult.Companion.error(ExceptionValues.BAD_REQUEST.getCode(), e.getDefaultMessage());

        // 保护内部信息,直接抛出SystemException 处理

        return AppResult.Companion.error(ExceptionValues.SERVER_INTERNAL_ERROR.getCode(), ExceptionValues.SERVER_INTERNAL_ERROR.getDefaultMessage());
    }

}
