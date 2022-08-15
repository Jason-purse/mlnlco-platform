package club.smileboy.mlnlco.service.config;

import club.smileboy.mlnlco.service.handler.QueryTypeHandleMethodArgumentResolver;
import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnumSerializerComposite;
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
                .ifPresent(action -> ValueEnumSerializerComposite.registerModule(((MappingJackson2HttpMessageConverter) action).getObjectMapper()));
    }
}
