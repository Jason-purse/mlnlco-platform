package club.smileboy.mlnlco.service.jackson;

import club.smileboy.mlnlco.commons.util.JsonUtil;
import club.smileboy.mlnlco.service.model.constant.ExceptionValues;
import club.smileboy.mlnlco.service.model.propertyEnum.AppSecret;
import club.smileboy.mlnlco.service.model.propertyEnum.AppSecretType;
import club.smileboy.mlnlco.service.model.propertyEnum.ApplicationType;
import club.smileboy.mlnlco.service.model.propertyEnum.ValueEnumSerializerComposite;
import club.smileboy.mlnlco.service.util.AppResult;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
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

    @Test
    public void kotlinDataSerialize() {

        JsonUtil util = JsonUtil.Companion.buildJsonUtil(objectMapper1 -> {
            // 设置
            objectMapper1.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            objectMapper1.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        });

        System.out.println(util.toJson(AppSecret.Companion.of(AppSecretType.NORMAL_SECRET,"12345").get()));

        System.out.println(JsonUtil.Companion.getINSTANCE().toJson(AppResult.Companion.success()));
    }

}
