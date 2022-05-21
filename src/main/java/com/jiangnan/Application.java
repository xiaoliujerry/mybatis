package com.jiangnan;

import com.jiangnan.annotation.MyComponent;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.jiangnan.mapper")
@EnableCaching
//@ComponentScan(includeFilters = {@ComponentScan.Filter(MyComponent.class)})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
