package com.hellozjf.test.cassandra.service.impl;

import com.hellozjf.test.cassandra.domain.Csadstate;
import com.hellozjf.test.cassandra.repository.CsadstateRepository;
import com.hellozjf.test.cassandra.service.CsadstateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@Service
@Slf4j
public class CsadstateServiceImpl implements CsadstateService {

    @Autowired
    private CsadstateRepository csadstateRepository;

    @Override
    public void printAll() {
        List<Csadstate> csadstateList = csadstateRepository.findAll();
        for (Csadstate csadstate : csadstateList) {
            log.debug("{}", csadstate);
        }
    }

    @Override
    @Cacheable(value = "csadstate", key = "#id")
    public Csadstate findById(String id) {
        return csadstateRepository.findById(id).orElse(new Csadstate());
    }

}
