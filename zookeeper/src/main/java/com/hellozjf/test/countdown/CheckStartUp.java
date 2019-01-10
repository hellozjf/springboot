package com.hellozjf.test.countdown;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
public class CheckStartUp {

    private static List<DangerCenter> stationList;
    private static CountDownLatch countDownLatch;

    public CheckStartUp() {

    }

    public static boolean checkAllStation() throws Exception {
        // 初始化3个调度站
        countDownLatch = new CountDownLatch(3);

        // 把所有站点添加进list
        stationList = new ArrayList<>();
        stationList.add(new StationBeijingIMooc(countDownLatch));
        stationList.add(new StationJiangsuSanling(countDownLatch));
        stationList.add(new StationShandongChangChuan(countDownLatch));

        // 使用线程池
        Executor executor = Executors.newFixedThreadPool(stationList.size());

        for (DangerCenter center : stationList) {
            executor.execute(center);
        }

        // 等待线程执行完毕
        countDownLatch.await();

        for (DangerCenter center : stationList) {
            if (! center.isOk()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        boolean result = CheckStartUp.checkAllStation();
        log.debug("监控中心针对所有危化品调度站的检查结果为：" + result);
    }
}
