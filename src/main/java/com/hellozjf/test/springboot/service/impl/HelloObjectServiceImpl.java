package com.hellozjf.test.springboot.service.impl;

import com.hellozjf.test.springboot.constant.ResultEnum;
import com.hellozjf.test.springboot.dao.HelloObjectRepository;
import com.hellozjf.test.springboot.dataobject.HelloObject;
import com.hellozjf.test.springboot.exception.HelloException;
import com.hellozjf.test.springboot.service.HelloObjectService;
import com.hellozjf.test.springboot.vo.HelloObjectVO;
import org.springframework.beans.BeanUtils;
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
    public HelloObjectVO findById(Long id) {
        HelloObject helloObject = helloObjectRepository.findById(id)
                .orElseThrow(() -> new HelloException(ResultEnum.CAN_NOT_FIND_THIS_ID_OBJECT));
        HelloObjectVO helloObjectVO = new HelloObjectVO();
        BeanUtils.copyProperties(helloObject, helloObjectVO);
        return helloObjectVO;
    }

    @Override
    public HelloObjectVO save(HelloObjectVO helloObjectVO) {
        HelloObject helloObject = new HelloObject();
        BeanUtils.copyProperties(helloObjectVO, helloObject);
        HelloObject resultHelloObject = helloObjectRepository.save(helloObject);
        HelloObjectVO returnHelloObjectVO = new HelloObjectVO();
        BeanUtils.copyProperties(resultHelloObject, returnHelloObjectVO);
        return returnHelloObjectVO;
    }

    @Override
    public void deleteById(Long id) {
        helloObjectRepository.deleteById(id);
    }
}
