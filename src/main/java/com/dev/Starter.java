package com.dev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@MapperScan(basePackages = "com.dev.dao")
@EnableScheduling  //开启定时任务环境配置
@SpringBootApplication
public class Starter extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
        SpringApplication.run(Starter.class);
    }

    /*设置web项目的启动入口*/

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Starter.class);
    }
}
