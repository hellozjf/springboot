package com.hellozjf.test.zookeeper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author Jingfeng Zhou
 */
@Data
@Slf4j
public class ZKGetChildrenList implements Watcher {

    private ZooKeeper zooKeeper = null;

    public static final String zkServerPath = "localhost:2181";
    public static final Integer timeout = 5000;

    public ZKGetChildrenList() {

    }

    public ZKGetChildrenList(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, timeout, new ZKGetChildrenList());
        } catch (IOException e) {
            log.error("e = {}", e);
            try {
                zooKeeper.close();
            } catch (InterruptedException e1) {
                log.error("e1 = {}", e1);
            }
        }
    }

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZKGetChildrenList zkServer = new ZKGetChildrenList(zkServerPath);

//        List<String> strChildList = zkServer.getZooKeeper().getChildren("/imooc", true);
//        for (String s : strChildList) {
//            log.debug("s = {}", s);
//        }

        // 异步调用
        String ctx = "{'callback':'ChildrenCallback'}";
//        zkServer.getZooKeeper().getChildren("/imooc", true, new ChildrenCallBack(), ctx);
        zkServer.getZooKeeper().getChildren("/imooc", true, new Children2CallBack(), ctx);

        countDownLatch.await();
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            if (event.getType() == Event.EventType.NodeDataChanged) {
                log.debug("NodeDataChanged");
            } else if (event.getType() == Event.EventType.NodeCreated) {
                log.debug("NodeCreated");
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                log.debug("NodeChildrenChanged");
                ZKGetChildrenList zkServer = new ZKGetChildrenList(zkServerPath);
                List<String> strChildList = zkServer.getZooKeeper().getChildren(event.getPath(), false);
                for (String s : strChildList) {
                    log.debug("s = {}", s);
                }
                countDownLatch.countDown();
            } else if (event.getType() == Event.EventType.NodeDeleted) {
                log.debug("NodeDeleted");
            }
        } catch (KeeperException e) {
            log.error("e = {}", e);
        } catch (InterruptedException e) {
            log.error("e = {}", e);
        }
    }
}
