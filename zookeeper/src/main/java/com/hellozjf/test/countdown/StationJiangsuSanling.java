package com.hellozjf.test.countdown;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
public class StationJiangsuSanling extends DangerCenter {

    public StationJiangsuSanling(CountDownLatch countDownLatch) {
        super(countDownLatch, "江苏三林调度站");
    }

    @Override
    public void check() {
        log.debug("正在检查[" + this.getStation() + "]...");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            log.error("e = {}", e);
        }

        log.debug("检查[" + this.getStation() + "]完毕，可以发车。");
    }
}
