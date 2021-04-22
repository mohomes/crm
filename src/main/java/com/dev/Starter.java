package com.dev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@MapperScan(basePackages = "com.dev.dao")
@SpringBootApplication
public class Starter
{
    public static void main( String[] args )
    {
        SpringApplication.run(Starter.class);
    }
}
