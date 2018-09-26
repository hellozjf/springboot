package com.hellozjf.test.springboot.service;

import com.hellozjf.test.springboot.vo.HelloObjectVO;

/**
 * @author Jingfeng Zhou
 */
public interface HelloObjectService {
    HelloObjectVO findById(Long id);
    HelloObjectVO save(HelloObjectVO helloObjectVO);
    void deleteById(Long id);
}
