package com.hellozjf.test.springboot.dubbo.provider;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@SpringBootApplication
public class DubboProviderDemo {
    public static void main(String[] args) {

        // 非 Web 应用
        new SpringApplicationBuilder(DubboProviderDemo.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
