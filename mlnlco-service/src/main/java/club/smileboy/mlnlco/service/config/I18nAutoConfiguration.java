package club.smileboy.mlnlco.service.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 11:29
 * @Description i18n 国际化配置
 */
@Configuration
public class I18nAutoConfiguration {

    /**
     * i18n message service
     */
    @Bean
    MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("i18n.business_exception");
        resourceBundleMessageSource.setDefaultEncoding("utf-8");
        resourceBundleMessageSource.setDefaultLocale(Locale.getDefault());
        return resourceBundleMessageSource;
    }
}
