package cn.leung.ai.aiselectclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.leung.ai.aiselectclient.mapper")
public class AiSelectClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiSelectClientApplication.class, args);
    }

}
