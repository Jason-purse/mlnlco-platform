package club.smileboy.mlnlco.service.model.propertyEnum;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 12:20
 * @Description 枚举值
 */
public interface ValueEnum<T extends Enum<T> & ValueEnum<T>> {


    /**
     * 返回对应枚举类型的枚举值 ..
     * @param tClass 枚举类型Class
     * @param <T> 枚举类型 - 泛型
     * @return Optional<T[]> 枚举值
     */
    static <T extends Enum<T> & ValueEnum<T>> Optional<T[]> listOf(Class<T> tClass) {
        return Optional.ofNullable(tClass.getEnumConstants());
    }

    /**
     * 返回对应的枚举类型的枚举值
     * @param tClass 枚举类型 Class
     * @param enumName 枚举名称
     * @param <T> 泛型
     * @return Optional<T> 枚举值
     */
    static <T extends Enum<T> & ValueEnum<T>> Optional<T> of(Class<T> tClass,String enumName) {
        return listOf(tClass).map(Arrays::asList)
                .flatMap(list -> list.stream().filter(ele -> ele.name().equalsIgnoreCase(enumName)).findFirst());
    }

    /**
     * 值序列化
     */
    class ValueEnumSerializer extends StdSerializer<ValueEnum> {

        protected ValueEnumSerializer() {
            super(ValueEnum.class);
        }

        @Override
        public void serialize(ValueEnum valueEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if(valueEnum instanceof PropertyEnum) {
                JsonSerializer<Object> valueSerializer = serializerProvider.findValueSerializer(PropertyEnum.PropertyEnumSerializer.class);
                valueSerializer.serialize(valueEnum,jsonGenerator,serializerProvider);
            }
            else {
                // 才直接解析(必然是一个枚举)
                jsonGenerator.writeString(((Enum<?>) valueEnum).name());
            }
        }
    }

    class ValueEnumDeserializer<T extends Enum<T> & ValueEnum<T>> extends StdDeserializer<ValueEnum<T>> {
        private Class<T> tClass;
        protected ValueEnumDeserializer(Class<T> vc) {
            super(vc);
            this.tClass = vc;
        }

        @Override
        public ValueEnum<T> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            String text = jsonParser.getText();
            return ValueEnum.of(tClass,text).orElseThrow(() -> ValueInstantiationException.from(jsonParser,"can't find a valid ValueEnum"));
        }
    }
}
