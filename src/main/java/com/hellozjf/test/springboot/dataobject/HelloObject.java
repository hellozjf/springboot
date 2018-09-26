package com.hellozjf.test.springboot.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Jingfeng Zhou
 */
@Data
@Entity
public class HelloObject {
    @Id
    @GeneratedValue
    private Long id;
    private Integer i;
    private Boolean flag;
    private Byte b;
    private Float f;
    private Double d;
    private String name;
    private Date time;
    private Character c;
}
