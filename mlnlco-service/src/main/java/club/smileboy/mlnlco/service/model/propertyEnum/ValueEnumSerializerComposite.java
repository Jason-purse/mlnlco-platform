package club.smileboy.mlnlco.service.model.propertyEnum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 16:58
 * @Description 序列化器集合
 */
public class ValueEnumSerializerComposite {

    private ValueEnumSerializerComposite() {

    }

    /**
     * 所有序列化器 ...
     */
    public static void serializers(SimpleModule module) {
        module.addSerializer(new ValueEnum.ValueEnumSerializer());
        module.addSerializer(new PropertyEnum.PropertyEnumSerializer());
    }

    /**
     * 所有反序列化器
     */
    public static void deserializers(SimpleModule module) {
        module.addDeserializer(ApplicationType.class,new PropertyEnum.PropertyEnumDeserializer<>(ApplicationType.class));
        module.addDeserializer(IcoType.class,new PropertyEnum.PropertyEnumDeserializer<>(IcoType.class));
        module.addDeserializer(AppSecretType.class,new PropertyEnum.PropertyEnumDeserializer<>(AppSecretType.class));
        // 暂时好像不需要 ValueEnum的 反序列化器 ..
    }

    /**
     * 为ObjectMapper注册 模块
     * @param objectMapper objectMapper
     */
    public static void registerModule(ObjectMapper objectMapper) {
        SimpleModule simpleModule = new SimpleModule();
        serializers(simpleModule);
        deserializers(simpleModule);
        objectMapper.registerModule(simpleModule);
    }
}
