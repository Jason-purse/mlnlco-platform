package club.smileboy.mlnlco.service.model.property;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 14:22
 * @Description 简单抽象的信息安全处理器
 */
public abstract class SimpleInfoSecurityHandler implements InfoSecurityHandler {

    private final String info;

    public SimpleInfoSecurityHandler(String info) {
        this.info = info;
    }

    /**
     * 原始信息
     */
    public String getInfo() {
        return info;
    }

    public static class SimpleInfoSecuritySerializer extends StdSerializer<SimpleInfoSecurityHandler>  {

        protected SimpleInfoSecuritySerializer() {
            super(SimpleInfoSecurityHandler.class);
        }

        @Override
        public void serialize(SimpleInfoSecurityHandler infoSecurityHandler, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        }
    }

    public static class SimpleInfoSecurityDeserializer<T  extends  SimpleInfoSecurityHandler> extends StdDeserializer<T> {

        protected SimpleInfoSecurityDeserializer(Class<T> vc) {
            super(vc);
        }

        @Override
        @SuppressWarnings("unchecked")
        public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            try {
                if(jsonParser.getText() == null) {
                    // 如果为null,直接返回null
                    return null;
                }
                return (T) BeanUtils.instantiateClass(handledType().getDeclaredConstructor(String.class),jsonParser.getText());
            }catch (Exception e) {
                e.printStackTrace();
                throw new UnsupportedOperationException(String.format("An instance of the specified type %s could not be instantiated", handledType().getName()));
            }
        }
    }
}
