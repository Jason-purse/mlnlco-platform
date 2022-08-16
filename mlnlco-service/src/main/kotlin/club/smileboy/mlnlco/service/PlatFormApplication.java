package club.smileboy.mlnlco.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 11:43
 * @Description 平台应用
 */
@SpringBootApplication
public class PlatFormApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlatFormApplication.class,args);
    }
}
