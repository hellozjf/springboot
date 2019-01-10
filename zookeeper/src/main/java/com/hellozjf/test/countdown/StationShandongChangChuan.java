package com.hellozjf.test.countdown;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
public class StationShandongChangChuan extends DangerCenter {

    public StationShandongChangChuan(CountDownLatch countDownLatch) {
        super(countDownLatch, "山东长川调度站");
    }

    @Override
    public void check() {
        log.debug("正在检查[" + this.getStation() + "]...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("e = {}", e);
        }

        log.debug("检查[" + this.getStation() + "]完毕，可以发车。");
    }
}
