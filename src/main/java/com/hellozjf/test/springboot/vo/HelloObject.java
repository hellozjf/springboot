package com.hellozjf.test.springboot.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Jingfeng Zhou
 */
@Data
public class HelloObject {
    private Boolean flag;
    private Byte b;
    private Integer id;
    private Long lid;
    private Float f;
    private Double d;
    private String name;
    private Date time;
    private Character c;
}
