package com.hellozjf.test.springboot.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Jingfeng Zhou
 */
@Entity
@Data
public class MyCacheEntity extends BaseEntity implements Serializable {
    private Integer age;
    private String name;
    private Date birthday;
    private Boolean bDied;
}
