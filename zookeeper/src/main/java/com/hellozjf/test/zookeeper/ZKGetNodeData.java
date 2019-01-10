package com.hellozjf.test.zookeeper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
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
public class ZKGetNodeData implements Watcher {

    private ZooKeeper zooKeeper = null;

    public static final String zkServerPath = "localhost:2181";
    public static final Integer timeout = 5000;
    public static Stat stat = new Stat();

    public ZKGetNodeData() {

    }

    public ZKGetNodeData(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, timeout, new ZKGetNodeData());
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
        ZKGetNodeData zkGetNodeData = new ZKGetNodeData(zkServerPath);

        byte[] resByte = zkGetNodeData.getZooKeeper().getData("/imooc", true, stat);
        String result = new String(resByte);
        log.debug("当前值: {}", result);
        countDownLatch.await();
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            if (event.getType() == Event.EventType.NodeDataChanged) {
                ZKGetNodeData zkGetNodeData = new ZKGetNodeData(zkServerPath);
                byte[] resByte = zkGetNodeData.getZooKeeper().getData("/imooc", false, stat);
                String result = new String(resByte);
                log.debug("更改后的值: " + result);
                log.debug("版本号变化dversion: " + stat.getVersion());
                countDownLatch.countDown();
            } else if (event.getType() == Event.EventType.NodeCreated) {

            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {

            } else if (event.getType() == Event.EventType.NodeDeleted) {

            }
        } catch (KeeperException e) {
            log.error("e = {}", e);
        } catch (InterruptedException e) {
            log.error("e = {}", e);
        }
    }
}
