package com.hellozjf.test.zookeeper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Jingfeng Zhou
 */
@Data
@Slf4j
public class ZKNodeExist implements Watcher {

    private ZooKeeper zooKeeper = null;

    public static final String zkServerPath = "localhost:2181";
    public static final Integer timeout = 5000;

    public ZKNodeExist() {

    }

    public ZKNodeExist(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, timeout, new ZKNodeExist());
        } catch (IOException e) {
            log.error("e = {}", e);
            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e1) {
                    log.error("e1 = {}", e1);
                }
            }
        }
    }

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZKNodeExist zkServer = new ZKNodeExist(zkServerPath);

        Stat stat = zkServer.getZooKeeper().exists("/imooc-fake", true);
        if (stat != null) {
            log.debug("查询的节点版本为dataVersion: " + stat.getVersion());
        } else {
            log.debug("该节点不存在...");
        }

        countDownLatch.await();
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeCreated) {
            log.debug("节点创建");
            countDownLatch.countDown();
        } else if (event.getType() == Event.EventType.NodeDataChanged) {
            log.debug("节点数据改变");
            countDownLatch.countDown();
        } else if (event.getType() == Event.EventType.NodeDeleted) {
            log.debug("节点删除");
            countDownLatch.countDown();
        }
    }
}
