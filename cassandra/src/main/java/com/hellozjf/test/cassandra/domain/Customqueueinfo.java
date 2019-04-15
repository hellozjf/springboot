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
public class Customqueueinfo implements Serializable {

    @PrimaryKey
    private CustomqueueinfoKey customqueueinfoKey;

    private String client_info;
    private String client_type;
    private Integer inform_state;
    private Long inform_time;
    private String nid;
    private String session_id;
    private Long time;

}
