package whu.web.psm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//方法级别权限控制
@SpringBootApplication
public class PsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsmApplication.class, args);
    }

}
