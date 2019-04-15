package com.hellozjf.test.springboot.domain;

import lombok.Data;

/**
 * @author Jingfeng Zhou
 */
@Data
public class FileObject extends FileObjectInfo {
    private String id;
    private String oid;
    private Long authTime = 0L;
    private byte[] data;
}
