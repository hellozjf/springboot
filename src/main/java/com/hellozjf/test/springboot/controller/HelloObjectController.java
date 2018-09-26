package com.hellozjf.test.springboot.controller;

import com.hellozjf.test.springboot.constant.ResultEnum;
import com.hellozjf.test.springboot.dataobject.HelloObject;
import com.hellozjf.test.springboot.exception.HelloException;
import com.hellozjf.test.springboot.form.HelloObjectForm;
import com.hellozjf.test.springboot.service.HelloObjectService;
import com.hellozjf.test.springboot.util.ResultUtils;
import com.hellozjf.test.springboot.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Jingfeng Zhou
 */
@RestController
@RequestMapping("/helloObject")
@Slf4j
public class HelloObjectController {

    @Autowired
    private HelloObjectService helloObjectService;

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable("id") Long id) {
        HelloObject helloObject = helloObjectService.findById(id);
        return ResultUtils.success(helloObject);
    }

    @PostMapping("/")
    public ResultVO post(@Valid HelloObjectForm helloObjectForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("【提交表单】参数不正确, helloObjectForm={}", helloObjectForm);
            throw new HelloException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        HelloObject helloObject = new HelloObject();
        BeanUtils.copyProperties(helloObjectForm, helloObject);
        HelloObject result = helloObjectService.save(helloObject);
        return ResultUtils.success(result);
    }

    @PostMapping("/validate")
    public ResultVO validate(@Valid HelloObjectForm helloObjectForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("【提交表单】参数不正确, helloObjectForm={}", helloObjectForm);
            throw new HelloException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtils.success();
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable("id") Long id) {
        try {
            helloObjectService.deleteById(id);
            return ResultUtils.success();
        } catch (EmptyResultDataAccessException e) {
            throw new HelloException(ResultEnum.CAN_NOT_FIND_THIS_ID_OBJECT);
        }
    }

    @PutMapping("/{id}")
    public ResultVO put(@PathVariable("id") Long id,
                                       HelloObject helloObject) {
        helloObject.setId(id);
        HelloObject result = helloObjectService.save(helloObject);
        return ResultUtils.success(result);
    }
}
