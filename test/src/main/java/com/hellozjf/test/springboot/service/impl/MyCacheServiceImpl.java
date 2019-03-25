package com.hellozjf.test.springboot.service.impl;

import com.hellozjf.test.springboot.entity.MyCacheEntity;
import com.hellozjf.test.springboot.repository.MyCacheRepository;
import com.hellozjf.test.springboot.service.MyCacheService;
import com.hellozjf.test.springboot.vo.MyCacheVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Jingfeng Zhou
 */
@Service
@Slf4j
public class MyCacheServiceImpl implements MyCacheService {

    @Autowired
    private MyCacheRepository myCacheRepository;

    @Cacheable(value = "myCache", key = "#id")
    @Override
    public MyCacheEntity findOne(String id) {
        log.debug("findOne id={}", id);
        return myCacheRepository.findById(id).get();
    }

    @CachePut(value = "myCache", key = "#result.id")
    @Override
    public MyCacheEntity putOne(MyCacheEntity myCacheEntity) {
        log.debug("putOne myCacheEntity={}", myCacheEntity);
        return myCacheRepository.save(myCacheEntity);
    }
}
