package com.hellozjf.test.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
public class Children2CallBack implements AsyncCallback.Children2Callback {
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        for (String s : children) {
            log.debug("s = {}", s);
        }
        log.debug("ChildrenCallback: {}", path);
        log.debug("ctx = {}", ctx);
        log.debug("stat = {}", stat.toString());
    }
}
