package com.hellozjf.test.springboot.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Jingfeng Zhou
 */
@Data
public class HelloObjectVO {
    private Long id;
    private Integer i;
    private Boolean flag;
    private Byte b;
    private Float f;
    private Double d;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    private Character c;
}
