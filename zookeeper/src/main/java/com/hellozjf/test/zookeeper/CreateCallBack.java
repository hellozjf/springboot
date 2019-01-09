package com.hellozjf.test.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
public class CreateCallBack implements AsyncCallback.StringCallback {
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        log.debug("创建节点：" + path);
        log.debug((String) ctx);
    }
}
