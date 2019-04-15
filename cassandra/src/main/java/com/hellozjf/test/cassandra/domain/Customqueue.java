package com.hellozjf.test.cassandra.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@Table
@Data
public class Customqueue implements Serializable {

    @PrimaryKey
    private String groupid;

    private List<String> queue_no;
}
