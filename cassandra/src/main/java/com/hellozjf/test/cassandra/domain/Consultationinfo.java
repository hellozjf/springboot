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
public class Consultationinfo implements Serializable {

    @PrimaryKey
    private ConsultationinfoKey consultationinfoKey;

    private String msgtype;
    private String parseresult;
    private String queryresult;
    private String question;
    private List<String> ztcodes;
}
