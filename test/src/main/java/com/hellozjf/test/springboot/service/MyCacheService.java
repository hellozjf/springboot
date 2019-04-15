package com.hellozjf.test.springboot.service;

import com.hellozjf.test.springboot.domain.MyCacheEntity;

/**
 * @author Jingfeng Zhou
 */
public interface MyCacheService {

    MyCacheEntity findOne(String id);
    MyCacheEntity putOne(MyCacheEntity myCacheEntity);
}
