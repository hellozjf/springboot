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
public class Sessionrestrict implements Serializable {

    @PrimaryKey
    private SessionrestrictKey sessionrestrictKey;

    private String csadid;
    private Integer inform_state;
    private Long lastupdate_time;
    private String reason;
    private Long restrict_time;
    private Integer state;

}
