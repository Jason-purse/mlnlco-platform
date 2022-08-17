package club.smileboy.mlnlco.service.model.property;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 14:58
 * @Description 属性序列化器 组合
 */
public class PropertySerializerComposite {

    private PropertySerializerComposite() {

    }

    /**
     * 配置 objectMapper
     * @param objectMapper objectMapper
     */
    public static void configure(ObjectMapper objectMapper) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(new SimpleInfoSecurityHandler.SimpleInfoSecuritySerializer());
        module.addDeserializer(Phone.class,new SimpleInfoSecurityHandler.SimpleInfoSecurityDeserializer<>(Phone.class));
        module.addDeserializer(Email.class,new SimpleInfoSecurityHandler.SimpleInfoSecurityDeserializer<>(Email.class));
        objectMapper.registerModule(module);
    }
}
