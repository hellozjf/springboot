package com.hellozjf.test.cassandra.service.impl;

import com.hellozjf.test.cassandra.service.CsadService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Action;

import static org.junit.Assert.*;

/**
 * @author Jingfeng Zhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CsadServiceImplTest {

    @Autowired
    private CsadService csadService;

    @Test
    public void getGroupId() {
        String groupId = csadService.getGroupId("6229a9a1c8b4452fbb63e704b25dbe6e");
        log.debug("groupId = {}", groupId);
    }
}