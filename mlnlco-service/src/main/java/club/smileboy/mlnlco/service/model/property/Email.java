package club.smileboy.mlnlco.service.model.property;

import club.smileboy.mlnlco.service.exception.AppUnSupportedOperationException;

import java.util.regex.Pattern;

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 14:42
 * @Description 邮箱校验 ...
 */
public class Email extends SimpleInfoSecurityHandler implements InfoValidationHandler {

    private final static Pattern EMAIL_PROTECT_PATTERN = Pattern.compile("(^\\\\w)[^@]*(@.*$)");

    private final static Pattern EMAIL_VALIDATION_PATTERN = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");

    public Email(String info) {
        super(info);
    }

    @Override
    public String eraseSensitiveInfo() {
        return EMAIL_PROTECT_PATTERN.matcher(getInfo()).replaceAll(matchResult -> matchResult.group(1) + "****" + matchResult.group(2));
    }

    @Override
    public void validationValidity() {
        if (!EMAIL_VALIDATION_PATTERN.matcher(getInfo()).matches()) {
            throw AppUnSupportedOperationException.Companion.ofEnableI18n("email.format");
        }
    }
}
