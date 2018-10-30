package com.hellozjf.test.springboot.config;

import com.hellozjf.test.springboot.util.JsonPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Jingfeng Zhou
 */
@Component
@PropertySource(
        value = "classpath:static/json/configprops.json",
        factory = JsonPropertySourceFactory.class
)
@ConfigurationProperties
@Data
public class JsonProperties {
    private int port;
    private boolean resend;
    private String host;
    private List<String> topics;
    private String useName;
    private Map<Integer, String> portPassword;
    private String none;
}
