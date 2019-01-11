package com.hellozjf.test.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
public class AclUtils {

    /**
     * 将id中的密码部分进行加密
     * 例如将imooc:imooc加密成为imooc:XwEDaL3J0JQGkRQzM0DpO6zMzZs=
     * @param id
     * @return
     * @throws Exception
     */
    public static String getDigestUserPwd(String id) throws Exception {
        return DigestAuthenticationProvider.generateDigest(id);
    }

    public static void main(String[] args) throws Exception {
        String id = "imooc:imooc";
        String idDigested = getDigestUserPwd(id);
        log.debug("idDigested = {}", idDigested);
    }
}
