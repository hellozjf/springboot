package com.hellozjf.test.springboot.controller;

import com.hellozjf.test.springboot.constant.ResultEnum;
import com.hellozjf.test.springboot.domain.HelloObject;
import com.hellozjf.test.springboot.service.AjaxService;
import com.hellozjf.test.springboot.util.ResultUtils;
import com.hellozjf.test.springboot.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Jingfeng Zhou
 */
@RestController
@RequestMapping("/ajax")
@Slf4j
public class AjaxController {

    @Autowired
    private AjaxService ajaxService;

    @GetMapping("/getHelloObjectById")
    public ResultVO getHelloObjectById(Long id) {
        HelloObject helloObject = ajaxService.getHelloObject(id);
        return ResultUtils.success(helloObject);
    }

    @PostMapping("/uploadFile")
    public ResultVO uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String str = new String(bytes);
            return ResultUtils.success(str);
        } catch (IOException e) {
            log.error("file error: {}", e);
            return ResultUtils.error(ResultEnum.FILE_ERROR.getCode(), ResultEnum.FILE_ERROR.getMessage());
        }
    }
}
