package com.hellozjf.test.springboot.service;

import com.hellozjf.test.springboot.dataobject.HelloObject;

/**
 * @author Jingfeng Zhou
 */
public interface AjaxService {
    HelloObject getHelloObject(Long id);
}
