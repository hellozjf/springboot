package com.hellozjf.test.cassandra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 该类中存储了所有的开关，以及所需要的bean
 *
 * @author Jingfeng Zhou
 */
@Configuration
@EnableCaching
@Slf4j
public class BeanConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
