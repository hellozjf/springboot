package com.hellozjf.test.cassandra.service.impl;

import com.hellozjf.test.cassandra.domain.Csadstate;
import com.hellozjf.test.cassandra.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Jingfeng Zhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GroupServiceImplTest {

    @Autowired
    private GroupService groupService;

    @Test
    public void getName() {
        String name = groupService.getName("112000000");
        Assert.assertEquals(name, "天津");
    }

    @Test
    public void getCsadList() {
        List<String> csadidList = groupService.getCsadList("ZRAR858");
        log.debug("csadidList = {}", csadidList);
    }
}