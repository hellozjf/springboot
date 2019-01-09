package com.hellozjf.test.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
public class ZKConnect implements Watcher {

    public static final String zkServerPath = "localhost:2181";
//    public static final String zkServerPath = "localhost:2181,localhost:2182,localhost:2183";
    public static final Integer timeout = 5000;

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.debug("接受到watch通知: {}", watchedEvent);
    }

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper(zkServerPath, timeout, new ZKConnect());

        log.debug("客户端开始连接zookeeper服务器...");
        log.debug("连接状态: {}", zk.getState());

        Thread.sleep(2000);

        log.debug("连接状态: {}", zk.getState());
    }
}
