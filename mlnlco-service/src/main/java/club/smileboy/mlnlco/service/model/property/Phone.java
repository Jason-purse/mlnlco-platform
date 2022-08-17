package club.smileboy.mlnlco.service.model.property;

import club.smileboy.mlnlco.service.exception.AppUnSupportedOperationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 14:20
 * @Description Phone info
 */
public class Phone extends SimpleInfoSecurityHandler implements InfoValidationHandler {

    /**
     * 空的,保护空异常
     */
    public final static Phone EMPTY = new Phone(null) {
        @Override
        public String eraseSensitiveInfo() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void validationValidity() {
            throw new UnsupportedOperationException();
        }
    };

    private final static Pattern PHONE_PROTECT_PATTERN = Pattern.compile("^(.{3}).+(.{4})$");

    private final static Pattern PHONE_VALIDATION_PATTERN = Pattern.compile("^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}$");

    public Phone(String info) {
        super(info);
    }


    @Override
    public String eraseSensitiveInfo() {
        return PHONE_PROTECT_PATTERN.matcher(getInfo()).replaceAll(matchResult -> matchResult.group(1) + "****" + matchResult.group(2));
    }

    @Override
    public void validationValidity() {
        Matcher matcher = PHONE_VALIDATION_PATTERN.matcher(getInfo());
        if(!matcher.matches()) {
            throw AppUnSupportedOperationException.Companion.ofEnableI18n("phone.format");
        }
    }
}
