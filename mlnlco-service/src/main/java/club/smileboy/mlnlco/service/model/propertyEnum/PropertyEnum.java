package club.smileboy.mlnlco.service.model.propertyEnum;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 12:26
 * @Description 属性枚举 ...
 */
public interface PropertyEnum<T extends Enum<T> & PropertyEnum<T>> extends ValueEnum<T> {
    /**
     * 转换为 String
     * @return 属性值
     */
    String  asText();

    /**
     * 通过属性值 转换为枚举 ...
     * @param tClass 枚举类型
     * @param value 字符串文本
     * @param <T> 泛型 PropertyEnum<T>
     * @return 对应的枚举类型
     */
    static <T extends Enum<T> & PropertyEnum<T>> Optional<T> of(Class<T> tClass, String value) {
        return ValueEnum.listOf(tClass)
                .map(Arrays::asList).flatMap(list -> list.stream().filter(ele -> ele.asText().equals(value)).findFirst());
    }

    /**
     * 基于 asText 序列化
     */
    class PropertyEnumSerializer extends StdSerializer<PropertyEnum> {

        protected PropertyEnumSerializer() {
            super(PropertyEnum.class);
        }

        @Override
        public void serialize(PropertyEnum propertyEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(propertyEnum.asText());
        }
    }

    /**
     * PropertyEnum 反序列化器 ..
     * @param <T> 泛型
     */
    class PropertyEnumDeserializer<T extends Enum<T> & PropertyEnum<T>> extends StdDeserializer<PropertyEnum<T>> {

        private final Class<T> tClass;
        protected PropertyEnumDeserializer(Class<T> tClass) {
            super(tClass);
            this.tClass = tClass;
        }



        @Override
        public PropertyEnum<T> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            String text = jsonParser.getText();
            return PropertyEnum.of(tClass,text).orElseThrow(() -> ValueInstantiationException.from(jsonParser,"can't find a valid propertyEnum type"));
        }
    }
}
