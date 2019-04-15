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
public class Messagetemp implements Serializable {

    @PrimaryKey
    private MessagetempKey messagetempKey;

    private String channel_id;
    private String content;
    private String groupid;
    private Integer msgtype;
    private String session_id;
}
