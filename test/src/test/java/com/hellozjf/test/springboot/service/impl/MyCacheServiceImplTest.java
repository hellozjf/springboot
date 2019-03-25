package com.hellozjf.test.springboot.service.impl;

import com.hellozjf.test.springboot.entity.MyCacheEntity;
import com.hellozjf.test.springboot.service.MyCacheService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Jingfeng Zhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MyCacheServiceImplTest {

    @Autowired
    private MyCacheService myCacheService;

    @Test
    public void putOne() {
        MyCacheEntity myCacheEntity = new MyCacheEntity();
        myCacheEntity.setAge(100);
        myCacheEntity.setBDied(true);
        myCacheEntity.setBirthday(new Date());
        myCacheEntity.setName("hello");
        myCacheEntity = myCacheService.putOne(myCacheEntity);

        MyCacheEntity myCacheEntity1 = myCacheService.findOne(myCacheEntity.getId());
        MyCacheEntity myCacheEntity2 = myCacheService.findOne(myCacheEntity.getId());
        log.debug("myCacheEntity1 = {}", myCacheEntity1);
        log.debug("myCacheEntity2 = {}", myCacheEntity2);

        log.debug("id = {}", myCacheEntity.getId());
    }

    @Test
    public void findOne() {
        MyCacheEntity myCacheEntity1 = myCacheService.findOne("4028b88169b2bbc10169b2bbcf830000");
        log.debug("myCacheEntity1 = {}", myCacheEntity1);
        MyCacheEntity myCacheEntity2 = myCacheService.findOne("4028b88169b2bbc10169b2bbcf830000");
        log.debug("myCacheEntity2 = {}", myCacheEntity2);
    }
}