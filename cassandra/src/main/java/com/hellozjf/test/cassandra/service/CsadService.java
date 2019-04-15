package com.hellozjf.test.cassandra.service;

/**
 * @author Jingfeng Zhou
 */
public interface CsadService {
    String getGroupId(String csadid);
    String getGroupName(String csadid);
    Integer getState(String csadid);
    String getName(String csadid);
    String getId(String csadid);
    Long getTodayServiceCount(String csadid);
    Long getTodayServiceDurationSecond(String csadid);
    Long getCurrentServiceCount(String csadid);
    Long getAverageTodayServiceDurationSecond(String csadid);
    Long getTodayMessageCount(String csadid);
    Long getTodaySatisfyCount(String csadid);
    Long getTodaySatisfyPercentage(String csadid);
}
