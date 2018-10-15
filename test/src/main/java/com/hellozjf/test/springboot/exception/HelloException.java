package com.hellozjf.test.springboot.exception;

import com.hellozjf.test.springboot.constant.ResultEnum;
import lombok.Getter;

/**
 * @author Jingfeng Zhou
 */
@Getter
public class HelloException extends RuntimeException {

    private Integer code;

    public HelloException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public HelloException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
