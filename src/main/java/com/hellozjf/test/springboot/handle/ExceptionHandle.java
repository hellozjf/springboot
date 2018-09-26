package com.hellozjf.test.springboot.handle;

import com.hellozjf.test.springboot.vo.ResultVO;
import com.hellozjf.test.springboot.exception.HelloException;
import com.hellozjf.test.springboot.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 廖师兄
 * 2017-01-21 13:59
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handle(Exception e) {
        if (e instanceof HelloException) {
            HelloException helloException = (HelloException) e;
            return ResultUtils.error(helloException.getCode(), helloException.getMessage());
        }else {
            logger.error("【系统异常】{}", e);
            return ResultUtils.error(-1, "未知错误");
        }
    }
}
