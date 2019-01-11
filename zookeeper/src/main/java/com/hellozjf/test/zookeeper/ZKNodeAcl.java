package com.hellozjf.test.zookeeper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@Data
@Slf4j
public class ZKNodeAcl implements Watcher {

    private ZooKeeper zooKeeper = null;

    public static final String zkServerPath = "localhost:2181";
    public static final Integer timeout = 5000;

    public ZKNodeAcl() {

    }

    public ZKNodeAcl(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, timeout, new ZKNodeAcl());
        } catch (IOException e) {
            log.error("e = {}", e);
            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void createZKNode(String path, byte[] data, List<ACL> acls) {
        String result = "";

        try {
            result = zooKeeper.create(path, data, acls, CreateMode.PERSISTENT);
            log.debug("创建节点: \t" + result + "\t成功...");
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ZKNodeAcl zkServer = new ZKNodeAcl(zkServerPath);

        // 任何人都可以访问
//        zkServer.createZKNode("/aclimooc", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);

        // 自定义用户认证访问
        List<ACL> acls = new ArrayList<>();
//        Id imooc1 = new Id("digest", AclUtils.getDigestUserPwd("imooc1:123456"));
//        Id imooc2 = new Id("digest", AclUtils.getDigestUserPwd("imooc2:123456"));
//        acls.add(new ACL(ZooDefs.Perms.ALL, imooc1));
//        acls.add(new ACL(ZooDefs.Perms.READ, imooc2));
//        acls.add(new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.CREATE, imooc2));
//        zkServer.createZKNode("/aclimooc/testdigest", "testdigest".getBytes(), acls);

        // 注册过的用户必须通过addAuthInfo才能操作节点，参考命令行addauth
//        zkServer.getZooKeeper().addAuthInfo("digest", "imooc1:123456".getBytes());
        // 创建目录
//        zkServer.createZKNode("/aclimooc/testdigest/childtest", "childtest".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL);
        // 获取值
//        Stat stat = new Stat();
//        byte[] data = zkServer.getZooKeeper().getData("/aclimooc/testdigest", false, stat);
//        log.debug("data = {}", new String(data));
        // 设置值，注意版本号
//        zkServer.getZooKeeper().setData("/aclimooc/testdigest", "now".getBytes(), 1);

        // ip方式的acl
//        List<ACL> aclsIP = new ArrayList<ACL>();
//        Id ipId1 = new Id("ip", "127.0.0.1");
//        aclsIP.add(new ACL(ZooDefs.Perms.ALL, ipId1));
//        zkServer.createZKNode("/aclimooc/iptest3", "iptest".getBytes(), aclsIP);

        // 验证ip是否有权限，注意ipv4和ipv6
        zkServer.getZooKeeper().setData("/aclimooc/iptest3", "now".getBytes(), 1);
//        Stat stat = new Stat();
//        byte[] data = zkServer.getZooKeeper().getData("/aclimooc/iptest3", false, stat);
//        log.debug("data = {}", new String(data));
//        log.debug("data = {}", stat.getVersion());
    }

    @Override
    public void process(WatchedEvent event) {

    }
}
