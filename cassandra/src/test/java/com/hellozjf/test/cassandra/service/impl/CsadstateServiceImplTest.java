package com.hellozjf.test.cassandra.service.impl;

import com.hellozjf.test.cassandra.domain.Csadstate;
import com.hellozjf.test.cassandra.repository.CsadstateRepository;
import com.hellozjf.test.cassandra.service.CsadstateService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Jingfeng Zhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CsadstateServiceImplTest {

    @Autowired
    private CsadstateService csadstateService;

    @Test
    public void findById() {
        Csadstate csadstate = csadstateService.findById("a9910acbe13b442da76caffa715131f4");
        log.debug("csadstate = {}", csadstate);
    }
}