package com.hellozjf.test.springboot.util;

import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * ZooKeeper连接和关闭的工具集
 * @author Jingfeng Zhou
 */
public class ZooKeeperConnectionUtils {

    public static ZooKeeper connect(String host) throws Exception {

        // 这个可以理解为一个信号量，它需要等待一个任务结束之后才能await返回
        CountDownLatch connectedSignal = new CountDownLatch(1);

        ZooKeeper zoo = new ZooKeeper(host,5000, watchedEvent -> {
            if (watchedEvent.getState() == KeeperState.SyncConnected) {
                // 连接成功之后，完成一个任务
                connectedSignal.countDown();
            }
        });

        // 等待连接任务完成
        connectedSignal.await();
        return zoo;
    }

    public static void close(ZooKeeper zoo) throws Exception {
        zoo.close();
    }
}
