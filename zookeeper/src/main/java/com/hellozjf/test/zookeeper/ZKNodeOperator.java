package com.hellozjf.test.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
public class ZKNodeOperator implements Watcher {

    private ZooKeeper zooKeeper = null;

    public static final String zkServerPath = "localhost:2181";
    public static final Integer timeout = 5000;

    public ZKNodeOperator() {

    }

    public ZKNodeOperator(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, timeout, new ZKNodeOperator());
        } catch (IOException e) {
            log.error("e = {}", e);
            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e1) {
                    log.error("e1 = {}");
                }
            }
        }
    }

    public void createZKNode(String path, byte[] data, List<ACL> acls) {
        String result = "";

        try {
            // 创建一个临时节点
//            zooKeeper.create(path, data, acls, CreateMode.EPHEMERAL);

            // 创建一个持久节点
            String ctx = "{'create':'success'}";
            zooKeeper.create(path, data, acls, CreateMode.PERSISTENT, new CreateCallBack(), ctx);

            log.debug("创建节点：\t" + result + "\t成功...");

            Thread.sleep(2000);
        } catch (Exception e) {
            log.error("e = {}", e);
        }
    }

    public static void main(String[] args) throws Exception {
        ZKNodeOperator zkServer = new ZKNodeOperator(zkServerPath);

        // 创建zk节点
        zkServer.createZKNode("/testnode", "testnode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);

        // 修改zk节点
//        Stat status = zkServer.getZooKeeper().setData("/testnode", "xyz".getBytes(), 0);
//        log.debug("status.getVersion() = {}", status.getVersion());

        // 删除zk节点，注意下面两句话要在5秒内完成执行
//        zkServer.createZKNode("/test-delete-node", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);
//        zkServer.getZooKeeper().delete("/test-delete-node", 0);
//        String ctx = "{'delete':'success'}";
//        zkServer.getZooKeeper().delete("/test-delete-node", 0, new DeleteCallBack(), ctx);
//        Thread.sleep(2000);
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {

    }
}
