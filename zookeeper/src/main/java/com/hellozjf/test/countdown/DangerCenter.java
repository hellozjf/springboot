package com.hellozjf.test.countdown;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
@Data
public abstract class DangerCenter implements Runnable {

    // 计数器
    private CountDownLatch countDownLatch;
    // 调度站
    private String station;
    // 调度站针对当前自己的站点进行检查，是否检查ok的标志
    private boolean ok;

    public DangerCenter(CountDownLatch countDownLatch, String station) {
        this.countDownLatch = countDownLatch;
        this.station = station;
        this.ok = false;
    }

    @Override
    public void run() {
        try {
            check();
            ok = true;
        } catch (Exception e) {
            log.error("e = {}", e);
            ok = false;
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    /**
     * 检查危化品车
     * 蒸罐
     * 汽油
     * 轮胎
     * gps
     * ……
     */
    public abstract void check();
}
