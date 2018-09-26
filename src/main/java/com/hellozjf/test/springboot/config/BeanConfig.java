package com.hellozjf.test.springboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellozjf.test.springboot.dataobject.HelloObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@Configuration
@Slf4j
public class BeanConfig {

    @Bean("helloObject")
    public HelloObject helloObject() {
        HelloObject helloObject = new HelloObject();
        helloObject.setB(new Byte("100"));
        helloObject.setC('C');
        helloObject.setD(12.56);
        helloObject.setF(1.2f);
        helloObject.setFlag(false);
        helloObject.setI(1024);
        helloObject.setId(11111111L);
        helloObject.setName("hello");
        helloObject.setTime(new Date());
        return helloObject;
    }

    @Bean("helloObject2")
    public HelloObject helloObject2() {
        HelloObject helloObject = new HelloObject();
        helloObject.setB(new Byte("-100"));
        helloObject.setC('D');
        helloObject.setD(78.56);
        helloObject.setF(123.42f);
        helloObject.setFlag(true);
        helloObject.setI(1024324);
        helloObject.setId(11111222L);
        helloObject.setName("helloworld");
        helloObject.setTime(new Date());
        return helloObject;
    }

    @Bean("helloObjectList")
    public List<HelloObject> helloObjectList() {
        List<HelloObject> helloObjectList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String s = String.valueOf(i);
            HelloObject helloObject = new HelloObject();
            helloObject.setB(Byte.valueOf(s));
            helloObject.setC(Character.forDigit(i, 10));
            helloObject.setD(Double.valueOf(s));
            helloObject.setF(Float.valueOf(s));
            helloObject.setFlag(i % 2 == 0 ? true : false);
            helloObject.setI(i);
            helloObject.setId((long) i * i * i);
            helloObject.setName(s);
            helloObject.setTime(new Date());
            helloObjectList.add(helloObject);
        }
        return helloObjectList;
    }

    @Bean
    public CommandLineRunner commandLineRunner(@Qualifier("helloObject2") final HelloObject helloObject,
                                               @Qualifier("helloObjectList") final List<HelloObject> helloObjectList) {
        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();
            log.debug("helloObject = {}", objectMapper.writeValueAsString(helloObject));
            log.debug("helloObjectList = {}", objectMapper.writeValueAsString(helloObjectList));

            // 测试URL编码
            String hello = "你好";
            String encodeHello = URLEncoder.encode(hello, "UTF-8");
            String decodeHello = URLDecoder.decode(encodeHello, "UTF-8");
            log.debug("encodeHello = {}, decodeHello = {}", encodeHello, decodeHello);

            // 测试时间
            Date date = new Date();
            log.debug("date = {}", date);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS.sss");
            log.debug("dateString = {}", simpleDateFormat.format(date));
        };
    }
}
