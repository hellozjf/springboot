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
public class Servicelog implements Serializable {

    @PrimaryKey
    private ServicelogKey servicelogKey;

    private String client_info;
    private Integer endtype;
    private Long service_endtime;
    private String service_id;
    private String servicedate;
    private String session_id;
}
