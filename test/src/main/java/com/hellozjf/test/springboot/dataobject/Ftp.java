package com.hellozjf.test.springboot.dataobject;

import lombok.Data;

/**
 * ftp链接常量
 */
@Data
public class Ftp {

    //ip地址
    private String ipAddr;

    //端口号
    private Integer port;

    //用户名
    private String userName;

    //密码
    private String pwd;

    //aaa路径
    private String path;

}
