package com.hellozjf.test.cassandra.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

/**
 * @author Jingfeng Zhou
 */
@Table
@Data
public class Otherconnectlog implements Serializable {

    @PrimaryKey
    private String client_id;
    private String nid;
    private Long timestamp;
}
