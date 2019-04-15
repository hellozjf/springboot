package com.hellozjf.test.cassandra.service;

import com.hellozjf.test.cassandra.domain.Csadstate;

/**
 * @author Jingfeng Zhou
 */
public interface CsadstateService {
    void printAll();
    Csadstate findById(String id);
}
