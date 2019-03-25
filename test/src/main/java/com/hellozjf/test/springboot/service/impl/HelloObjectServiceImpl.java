package com.hellozjf.test.springboot.service.impl;

import com.hellozjf.test.springboot.constant.ResultEnum;
import com.hellozjf.test.springboot.repository.HelloObjectRepository;
import com.hellozjf.test.springboot.entity.HelloObject;
import com.hellozjf.test.springboot.exception.HelloException;
import com.hellozjf.test.springboot.service.HelloObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jingfeng Zhou
 */
@Service
public class HelloObjectServiceImpl implements HelloObjectService {

    @Autowired
    private HelloObjectRepository helloObjectRepository;

    @Override
    public HelloObject findById(Long id) {
        HelloObject helloObject = helloObjectRepository.findById(id)
                .orElseThrow(() -> new HelloException(ResultEnum.CAN_NOT_FIND_THIS_ID_OBJECT));
        return helloObject;
    }

    @Override
    public HelloObject save(HelloObject helloObject) {
        HelloObject result = helloObjectRepository.save(helloObject);
        return result;
    }

    @Override
    public void deleteById(Long id) {
        helloObjectRepository.deleteById(id);
    }
}
