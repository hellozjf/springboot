package com.hellozjf.test.springboot.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Jingfeng Zhou
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    CAN_NOT_FIND_THIS_ID_OBJECT(1, "无法找到此ID对应的对象"),
    PARAM_ERROR(2, "参数错误"),
    ;

    Integer code;
    String message;
}
