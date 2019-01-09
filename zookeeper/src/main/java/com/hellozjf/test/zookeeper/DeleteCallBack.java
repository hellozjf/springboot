package com.hellozjf.test.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
public class DeleteCallBack implements AsyncCallback.VoidCallback {

    @Override
    public void processResult(int rc, String path, Object ctx) {
        log.debug("删除节点：" + path);
        log.debug((String) ctx);
    }
}
