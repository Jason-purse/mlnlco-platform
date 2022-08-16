package club.smileboy.mlnlco.service.jackson;

import club.smileboy.mlnlco.commons.util.EncryptUtil;
import org.junit.jupiter.api.Test;

/**
 * @author FLJ
 * @date 2022/8/16
 * @time 13:53
 * @Description 加密算法测试
 */
public class EncryptTests {

    @Test
    public void normalEncryptTests() {

        String result = EncryptUtil.INSTANCE.generateNormalEncryptStringsWithBase64("123456789");
        System.out.println(result);

    }
}
