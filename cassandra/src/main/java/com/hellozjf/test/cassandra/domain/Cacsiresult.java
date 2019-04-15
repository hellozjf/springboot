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
public class Cacsiresult implements Serializable {

    @PrimaryKey
    private CacsiresultKey cacsiresultKey;

    private String cacsi;
    private String sessionid;

}
