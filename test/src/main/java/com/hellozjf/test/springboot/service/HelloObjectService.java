package com.hellozjf.test.springboot.service;

import com.hellozjf.test.springboot.entity.HelloObject;

/**
 * @author Jingfeng Zhou
 */
public interface HelloObjectService {
    HelloObject findById(Long id);
    HelloObject save(HelloObject helloObject);
    void deleteById(Long id);
}
