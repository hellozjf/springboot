package com.hellozjf.test.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jingfeng Zhou
 */
@Data
@Component
@ConfigurationProperties("file")
public class FileConfig {
    private boolean writeToFile;
}
