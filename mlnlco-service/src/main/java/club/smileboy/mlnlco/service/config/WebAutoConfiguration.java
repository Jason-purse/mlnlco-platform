package club.smileboy.mlnlco.service.config;

import club.smileboy.mlnlco.commons.util.JsonUtil;
import club.smileboy.mlnlco.service.handler.QueryTypeHandleMethodArgumentResolver;
import club.smileboy.mlnlco.service.model.property.PropertySerializerComposite;
import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnumSerializerComposite;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebAutoConfiguration implements WebMvcConfigurer  {

    @Override
    public void addArgumentResolvers(@NotNull List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new QueryTypeHandleMethodArgumentResolver());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream().filter(converter -> converter instanceof MappingJackson2HttpMessageConverter).findFirst()
                .ifPresent(action -> {
                    ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) action).getObjectMapper();
                    ValueEnumSerializerComposite.registerModule(objectMapper);
                    PropertySerializerComposite.configure(objectMapper);
                    JsonUtil.Companion.registerKotlinSupport(objectMapper);
                });
    }
}
