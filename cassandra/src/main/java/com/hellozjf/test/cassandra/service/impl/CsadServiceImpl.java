package com.hellozjf.test.cassandra.service.impl;

import com.hellozjf.test.cassandra.domain.Csadstate;
import com.hellozjf.test.cassandra.service.CsadService;
import com.hellozjf.test.cassandra.service.CsadstateService;
import com.hellozjf.test.cassandra.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jingfeng Zhou
 */
@Service
public class CsadServiceImpl implements CsadService {

    @Autowired
    private CsadstateService csadstateService;

    @Autowired
    private GroupService groupService;

    @Override
    public String getGroupId(String csadid) {
        Csadstate csadstate = csadstateService.findById(csadid);
        return csadstate.getGroupid();
    }

    @Override
    public String getGroupName(String csadid) {
        Csadstate csadstate = csadstateService.findById(csadid);
        return groupService.getName(csadstate.getGroupid());
    }

    @Override
    public Integer getState(String csadid) {
        Csadstate csadstate = csadstateService.findById(csadid);
        // todo 这里要判断该坐席在customservice表中是否有服务
        return csadstate.getCsadstate();
    }

    @Override
    public String getName(String csadid) {
        // todo 这里要查询oracle数据库
        return null;
    }

    @Override
    public String getId(String csadid) {
        return csadid;
    }

    @Override
    public Long getTodayServiceCount(String csadid) {
        return null;
    }

    @Override
    public Long getTodayServiceDurationSecond(String csadid) {
        return null;
    }

    @Override
    public Long getCurrentServiceCount(String csadid) {
        return null;
    }

    @Override
    public Long getAverageTodayServiceDurationSecond(String csadid) {
        return null;
    }

    @Override
    public Long getTodayMessageCount(String csadid) {
        return null;
    }

    @Override
    public Long getTodaySatisfyCount(String csadid) {
        return null;
    }

    @Override
    public Long getTodaySatisfyPercentage(String csadid) {
        return null;
    }
}
