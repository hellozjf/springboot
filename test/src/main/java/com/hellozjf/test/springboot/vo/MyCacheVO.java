package com.hellozjf.test.springboot.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jingfeng Zhou
 */
@Data
public class MyCacheVO implements Serializable {
    private String id;
    private Integer age;
    private String name;
    private Date birthday;
    private Boolean bDied;
}
