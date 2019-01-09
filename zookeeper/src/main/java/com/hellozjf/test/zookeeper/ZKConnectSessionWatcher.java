package com.hellozjf.test.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
public class ZKConnectSessionWatcher implements Watcher {

    public static final String zkServerPath = "localhost:2181";
    public static final Integer timeout = 5000;

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper(zkServerPath, timeout, new ZKConnectSessionWatcher());

        Thread.sleep(1000);

        long sessionId = zk.getSessionId();
        String ssid = "0x" + Long.toHexString(sessionId);
        log.debug("ssid={}", ssid);
        byte[] sessionPassword = zk.getSessionPasswd();

        log.debug("客户端开始连接zookeeper服务器...");
        log.debug("连接状态: {}", zk.getState());
        Thread.sleep(1000);
        log.debug("连接状态: {}", zk.getState());

        Thread.sleep(200);

        log.debug("开始会话重连...");

        ZooKeeper zkSession = new ZooKeeper(zkServerPath, timeout, new ZKConnectSessionWatcher(), sessionId, sessionPassword);
        log.debug("重新连接状态zkSession: {}", zkSession.getState());
        Thread.sleep(1000);
        log.debug("重新连接状态zkSession: {}", zkSession.getState());
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.debug("接受到watch通知: {}", watchedEvent);
    }
}
