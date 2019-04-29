package com.hellozjf.test.springboot.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

/**
 * @author Jingfeng Zhou
 */
@Table
@Data
public class Csadstate implements Serializable {

    @PrimaryKey
    private String csadid;

    private Integer csadstate;

    private String groupid;

    private Integer maxservicenum;

    private String protocol;

    private Integer servicenum;
}
