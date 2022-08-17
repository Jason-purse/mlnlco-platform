package club.smileboy.mlnlco.service.advice;

import club.smileboy.mlnlco.service.component.I18nMessageService;
import club.smileboy.mlnlco.service.exception.SystemException;
import club.smileboy.mlnlco.service.model.constant.ExceptionValues;
import club.smileboy.mlnlco.service.util.AppResult;
import club.smileboy.mlnlco.service.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
        systemException.printStackTrace();
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
     * 内部无法处理
     *
     * @param e 无法操作的异常
     * @return 错误消息 ..
     */
    @ExceptionHandler(UnsupportedOperationException.class)
    public AppResult<?> handleUnSupportedOperationException(UnsupportedOperationException e) {
        e.printStackTrace();
        try {
            return AppResult.Companion.error(ExceptionValues.UN_SUPPORT_ERROR.getCode(), messageService.resolveMessageForException(
                    ExceptionValues.UN_SUPPORT_ERROR.getCode(), RequestUtil.INSTANCE.getRequestLocale(), e.getMessage()
            ));
        } catch (Exception ee) {
            throw new SystemException(ee.getMessage());
        }
    }

}
