package com.hellozjf.test.springboot.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 这里说明一下，@NotEmpty 用在集合类上面，@NotBlank 用在String上面，@NotNull    用在基本类型上
 * 详情请见：https://www.cnblogs.com/softidea/archive/2016/11/08/6044123.html
 *
 * @author Jingfeng Zhou
 */
@Data
@ToString
public class HelloObjectForm {

    private Long id;

    @NotNull(message = "i必填")
    private Integer i;

    @NotNull(message = "flag必填")
    private Boolean flag;

    @NotNull(message = "b必填")
    private Byte b;

    @NotNull(message = "f必填")
    private Float f;

    @NotNull(message = "d必填")
    private Double d;

    @NotBlank(message = "name必填")
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date time;

    @NotNull(message = "c必填")
    private Character c;
}
