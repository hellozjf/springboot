package com.hellozjf.test.springboot.controller;

import com.hellozjf.test.springboot.service.HelloObjectService;
import com.hellozjf.test.springboot.util.ResultUtils;
import com.hellozjf.test.springboot.vo.HelloObjectVO;
import com.hellozjf.test.springboot.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jingfeng Zhou
 */
@RestController
@RequestMapping("/helloObject")
public class HelloObjectController {

    @Autowired
    private HelloObjectService helloObjectService;

    @GetMapping("/{id}")
    public ResultVO<HelloObjectVO> get(@PathVariable("id") Long id) {
        HelloObjectVO helloObjectVO = helloObjectService.findById(id);
        return ResultUtils.success(helloObjectVO);
    }

    @PostMapping("/")
    public ResultVO<HelloObjectVO> post(HelloObjectVO helloObjectVO) {
        helloObjectVO.setId(null);
        HelloObjectVO resultHelloObjectVO = helloObjectService.save(helloObjectVO);
        return ResultUtils.success(resultHelloObjectVO);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable("id") Long id) {
        helloObjectService.deleteById(id);
        return ResultUtils.success();
    }

    @PutMapping("/{id}")
    public ResultVO<HelloObjectVO> put(@PathVariable("id") Long id,
                                       HelloObjectVO helloObjectVO) {
        helloObjectVO.setId(id);
        HelloObjectVO resultHelloObjectVO = helloObjectService.save(helloObjectVO);
        return ResultUtils.success(resultHelloObjectVO);
    }
}
