package club.smileboy.mlnlco.service.model.constant;

import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnum;
import org.springframework.util.Assert;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 15:26
 * @Description 异常值 ..
 */
public enum ExceptionValues implements ValueEnum<ExceptionValues> {

    SUCCESS(200,"SUCCESS"),
    BAD_REQUEST(400,"BAD_REQUEST"),
    FORBIDDEN(403,"FORBIDDEN"),
    UN_AUTHENTICATION(401,"UN_AUTHENTICATION"),
    SERVER_INTERNAL_ERROR(500,"SERVER_INTERNAL_ERROR"),
    /**
     * 不支持异常 ..
     */
    UN_SUPPORT_ERROR(600,"UN_SUPPORT_OPERATION_ERROR");

    ExceptionValues(Integer value,String defaultMessage) {
        Assert.notNull(value,"error code must not be null !!!");
        this.code = value;
        this.defaultMessage  = defaultMessage;
    }

    private final Integer code;

    private final String defaultMessage;

    public Integer getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
