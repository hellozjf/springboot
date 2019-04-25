package com.hellozjf.test.springboot.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Jingfeng Zhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MyDateUtilsTest {

    @Test
    public void getTimeList() {
        List<Long> timeList = MyDateUtils.getTimeList(2019, 3, 31);
        Assert.assertEquals(1553961600000L, timeList.get(0).longValue());
        Assert.assertEquals(1554048000000L, timeList.get(timeList.size() - 1).longValue());
        timeList = MyDateUtils.getTimeList(2018, 12, 31);
        Assert.assertEquals(1546185600000L, timeList.get(0).longValue());
        Assert.assertEquals(1546272000000L, timeList.get(timeList.size() - 1).longValue());
        timeList = MyDateUtils.getTimeList(2019, 2, null);
        Assert.assertEquals(1548950400000L, timeList.get(0).longValue());
        Assert.assertEquals(1551369600000L, timeList.get(timeList.size() - 1).longValue());
        timeList = MyDateUtils.getTimeList(2018, null, null);
        Assert.assertEquals(1514736000000L, timeList.get(0).longValue());
        Assert.assertEquals(1546272000000L, timeList.get(timeList.size() - 1).longValue());
    }
}