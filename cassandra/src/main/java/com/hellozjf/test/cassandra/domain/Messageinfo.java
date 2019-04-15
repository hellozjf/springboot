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
public class Messageinfo implements Serializable {

    @PrimaryKey
    private MessageinfoKey messageinfoKey;

    private String channel_from;
    private String channel_to;
    private String content;
    private String fromuser;
    private Integer msgtype;
    private Integer num_send;
    private String session_id;
    private Integer state;
    private Integer stype;
    private Long time_receipt;
    private Long time_send;
    private String touser;
    private Integer type;

}
