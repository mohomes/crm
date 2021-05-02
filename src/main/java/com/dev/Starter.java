package com.dev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@MapperScan(basePackages = "com.dev.dao")
@EnableScheduling  //开启定时任务环境配置
@SpringBootApplication
public class Starter
{
    public static void main( String[] args )
    {
        SpringApplication.run(Starter.class);
    }
}
