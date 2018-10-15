package com.hellozjf.test.springboot.service.impl;

import com.hellozjf.test.springboot.constant.ResultEnum;
import com.hellozjf.test.springboot.dao.HelloObjectRepository;
import com.hellozjf.test.springboot.dataobject.HelloObject;
import com.hellozjf.test.springboot.exception.HelloException;
import com.hellozjf.test.springboot.service.AjaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Jingfeng Zhou
 */
@Service
public class AjaxServiceImpl implements AjaxService {

    @Autowired
    private HelloObjectRepository helloObjectRepository;

    @Override
    public HelloObject getHelloObject(Long id) {
        Optional<HelloObject> optionalHelloObject = helloObjectRepository.findById(id);
        // 以下写法参考https://blog.csdn.net/lsmsrc/article/details/41747241
        return optionalHelloObject.orElseThrow(() -> new HelloException(ResultEnum.CAN_NOT_FIND_THIS_ID_OBJECT));
    }
}
