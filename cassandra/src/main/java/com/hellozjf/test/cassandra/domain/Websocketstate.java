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
public class Websocketstate implements Serializable {

    @PrimaryKey
    private WebsocketstateKey websocketstateKey;

    private Long connect_time;
    private Integer state;
}
