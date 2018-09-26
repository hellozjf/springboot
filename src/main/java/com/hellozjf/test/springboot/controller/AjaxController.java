package com.hellozjf.test.springboot.controller;

import com.hellozjf.test.springboot.dataobject.HelloObject;
import com.hellozjf.test.springboot.service.AjaxService;
import com.hellozjf.test.springboot.util.ResultUtils;
import com.hellozjf.test.springboot.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jingfeng Zhou
 */
@RestController
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    private AjaxService ajaxService;

    @GetMapping("/getHelloObjectById")
    public ResultVO<HelloObject> getHelloObjectById(Long id) {
        HelloObject helloObject = ajaxService.getHelloObject(id);
        return ResultUtils.success(helloObject);
    }
}
