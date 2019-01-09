package com.hellozjf.test.springboot.vo;

import lombok.Data;

/**
 * @author Jingfeng Zhou
 */
@Data
public class BaiduTokenVO {
    private String token;
    private Integer expire;
}
