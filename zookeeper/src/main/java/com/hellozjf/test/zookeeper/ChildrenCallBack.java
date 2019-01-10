package com.hellozjf.test.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;

import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
public class ChildrenCallBack implements AsyncCallback.ChildrenCallback {
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children) {
        for (String s : children) {
            log.debug("s = {}", s);
        }
        log.debug("ChildrenCallback: {}", path);
        log.debug("ctx = {}", ctx);
    }
}
