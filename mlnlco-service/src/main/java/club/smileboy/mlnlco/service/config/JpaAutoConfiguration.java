package club.smileboy.mlnlco.service.config;

import club.smileboy.mlnlco.service.model.propertyEnum.AppSecret;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

/**
 * @author JASONJ
 * @date 2022/8/15
 * @time 22:23
 * @description jpa 相关的一些属性处理器注册
 **/
@Configuration
public class JpaAutoConfiguration {

    public static class AppPersistenceUnitPostProcessor implements PersistenceUnitPostProcessor {
        @Override
        public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo info) {
            info.addManagedClassName(AppSecret.Companion.AppSecretAttributeConverter.class.getName());
        }
    }


    @Bean
    EntityManagerFactoryBuilderCustomizer entityManagerFactoryBuilderCustomizer(
            ConfigurableListableBeanFactory factory) {
        return builder -> builder.setPersistenceUnitPostProcessors(
                new AppPersistenceUnitPostProcessor());
    }
}
