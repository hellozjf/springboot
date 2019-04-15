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
public class Customservice implements Serializable {

    @PrimaryKey
    private CustomserviceKey customServiceKey;
    private Integer apply_type;
    private Integer cacsi_inform_state;
    private String client_info;
    private String client_type;
    private String nid;
    private Integer overtime_client_inform_state;
    private Long service_time;
    private Integer session_client_inform_state;
    private Integer session_csad_inform_state;
    private String session_id;
    private Long time_client_lastest;
    private Long time_csad_lastest;
}
