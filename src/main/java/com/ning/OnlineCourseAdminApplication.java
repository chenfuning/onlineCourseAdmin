package com.ning;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//掃描mybatis mapper包路徑
@MapperScan(basePackages = "com.ning.mapper")
//扫描所需要的包，包含自用工具类所在路径
//扫描所需要的包，包含自用工具类所在路径
@ComponentScan(basePackages = {"com.ning","org.n3r.idworker"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OnlineCourseAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineCourseAdminApplication.class, args);
    }

}
