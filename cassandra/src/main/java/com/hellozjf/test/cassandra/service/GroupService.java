package com.hellozjf.test.cassandra.service;

import java.util.List;

/**
 * @author Jingfeng Zhou
 */
public interface GroupService {
    String getName(String groupid);
    List<String> getCsadList(String groupid);
}
