package com.hellozjf.test.springboot.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hellozjf.test.springboot.vo.HelloObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        helloObject.setId(1024);
        helloObject.setLid(11111111L);
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
        helloObject.setId(1024324);
        helloObject.setLid(11111222L);
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
            helloObject.setId(i);
            helloObject.setLid((long) i * i * i);
            helloObject.setName(s);
            helloObject.setTime(new Date());
            helloObjectList.add(helloObject);
        }
        return helloObjectList;
    }

    @Bean
    public CommandLineRunner commandLineRunner(@Qualifier("helloObject2") HelloObject helloObject,
                                               @Qualifier("helloObjectList") List<HelloObject> helloObjectList) {
        return args -> {
            Object object = JSON.toJSON(helloObject);
            if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                log.debug("jsonObject = {}", jsonObject);
            } else {
                log.debug("object = {}", object);
            }

            object = JSON.toJSON(helloObjectList);
            if (object instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) object;
                log.debug("jsonArray = {}", jsonArray);
            } else {
                log.debug("object = {}", object);
            }
        };
    }
}
