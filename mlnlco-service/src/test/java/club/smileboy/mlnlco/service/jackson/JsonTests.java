package club.smileboy.mlnlco.service.jackson;

import club.smileboy.mlnlco.service.exception.ExceptionValues;
import club.smileboy.mlnlco.service.model.propertyEnum.ApplicationType;
import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnumSerializerComposite;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author JASONJ
 * @date 2022/8/15
 * @time 21:55
 * @description 序列化测试
 **/
public class JsonTests {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void beforeConfig() {
        ValueEnumSerializerComposite.registerModule(objectMapper);
    }

    @Test
    public void test() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(ExceptionValues.BAD_REQUEST));

        System.out.println(objectMapper.writeValueAsString(ApplicationType.CLIENT));
    }

}
