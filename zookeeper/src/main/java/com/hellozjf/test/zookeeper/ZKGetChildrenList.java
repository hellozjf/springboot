package com.hellozjf.test.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author Jingfeng Zhou
 */
public class ZKGetChildrenList implements Watcher {

    private ZooKeeper zooKeeper = null;

    @Override
    public void process(WatchedEvent event) {

    }
}
