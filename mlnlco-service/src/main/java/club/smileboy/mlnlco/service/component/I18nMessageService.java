package club.smileboy.mlnlco.service.component;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

/**
 * @author FLJ
 * @date 2022/8/16
 * @time 17:04
 * @Description 国际化 消息服务
 */
@Service
public class I18nMessageService implements I18nMessageCodeResolver, MessageSourceAware {

    private MessageSource messageSource;


    @Override
    public Optional<String> resolveMessage(@NotNull String messagePrefix, String code, Locale locale, @Nullable Object[] args) throws IllegalAccessException {
        if(messageSource == null) {
            throw new IllegalAccessException("current service maybe is not fully initialized !!!");
        }
        try {
            return Optional.of(messageSource.getMessage(String.format("%s%s", messagePrefix,code),args,locale));
        }catch (Exception e) {
            // pass
        }
        return Optional.empty();
    }

    @Override
    public void setMessageSource(@NotNull MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
