package com.hellozjf.test.cassandra.service.impl;

import com.hellozjf.test.cassandra.domain.Customservice;
import com.hellozjf.test.cassandra.repository.CustomserviceRepository;
import com.hellozjf.test.cassandra.service.CustomserviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@Service
@Slf4j
public class CustomserviceServiceImpl implements CustomserviceService {

    @Autowired
    private CustomserviceRepository customserviceRepository;

    @Override
    public void printAll() {
        List<Customservice> customserviceList = customserviceRepository.findAll();
        for (Customservice customservice : customserviceList) {
            log.debug("{}", customservice);
        }
    }
}
