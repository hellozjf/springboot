package com.hellozjf.test.springboot.controller;

import com.hellozjf.test.springboot.constant.ResultEnum;
import com.hellozjf.test.springboot.domain.HelloObject;
import com.hellozjf.test.springboot.exception.HelloException;
import com.hellozjf.test.springboot.form.HelloObjectForm;
import com.hellozjf.test.springboot.service.HelloObjectService;
import com.hellozjf.test.springboot.util.ResultUtils;
import com.hellozjf.test.springboot.vo.BaiduTokenVO;
import com.hellozjf.test.springboot.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@RestController
@RequestMapping("/helloObject")
@Slf4j
public class HelloObjectController {

    @Autowired
    private HelloObjectService helloObjectService;

    @Autowired
    private BaiduTokenVO baiduTokenVO;

    @GetMapping("/printBaiduTokenVO")
    public ResultVO printBaiduTokenVO() {
        return ResultUtils.success(baiduTokenVO);
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable("id") Long id) {
        HelloObject helloObject = helloObjectService.findById(id);
        return ResultUtils.success(helloObject);
    }

    @PostMapping("/")
    public ResultVO post(@Valid HelloObjectForm helloObjectForm,
                         BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            log.error("【提交表单】参数不正确, helloObjectForm={}", helloObjectForm);
            throw new HelloException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        HelloObject helloObject = new HelloObject();
        BeanUtils.copyProperties(helloObjectForm, helloObject);
        helloObject.setData(helloObjectForm.getData().getBytes("UTF-8"));
        HelloObject result = helloObjectService.save(helloObject);
        return ResultUtils.success(result);
    }

    @PostMapping("/validate")
    public ResultVO validate(@Valid HelloObjectForm helloObjectForm,
                         BindingResult bindingResult) {

        log.debug("enter validate");

        if (bindingResult.hasErrors()) {
            log.error("【提交表单】参数不正确, helloObjectForm={}", helloObjectForm);
            throw new HelloException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtils.success();
    }

    @PostMapping(value = "/validateArray", consumes = "text/plain")
    public ResultVO validateArray(@RequestBody String array) {
        log.debug("enter validateArray");
        log.debug("array={}", array);
        return ResultUtils.success();
    }

    @PostMapping(value = "/validateJSONArray", consumes = "application/json")
    public ResultVO validateJSONArray(@RequestBody List<HelloObjectForm> helloObjectFormList) {
        log.debug("enter validateJSONArray");
        log.debug("helloObjectFormList={}", helloObjectFormList);
        return ResultUtils.success();
    }

    @PostMapping(value = "/validateJSONArrayParam")
    public ResultVO validateJSONArrayParam(@RequestParam String helloObjectFormList) {
        log.debug("enter validateJSONArrayParam");
        log.debug("helloObjectFormList={}", helloObjectFormList);
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
