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
public class Msgservicestate implements Serializable {

    @PrimaryKey
    private String uid;

    private Integer auto;
    private String channel_id;
    private Integer hstate;
    private String nid;
    private Integer state;
    private Long time;

}
