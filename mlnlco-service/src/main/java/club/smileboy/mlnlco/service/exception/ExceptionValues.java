package club.smileboy.mlnlco.service.exception;

import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnum;
import org.springframework.util.Assert;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 15:26
 * @Description 异常值 ..
 */
public enum ExceptionValues implements ValueEnum<ExceptionValues> {

    SUCCESS(200),
    BAD_REQUEST(400),
    FORBIDDEN(403),
    UN_AUTHENTICATION(401),
    SERVER_INTERNAL_ERROR(500);

    ExceptionValues(Integer value) {
        Assert.notNull(value,"error code must not be null !!!");
        this.code = value;
    }

    private final Integer code;

    public Integer getCode() {
        return code;
    }
}
