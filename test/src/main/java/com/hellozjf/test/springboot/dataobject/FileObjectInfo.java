package com.hellozjf.test.springboot.dataobject;

import lombok.Data;

/**
 * @author Jingfeng Zhou
 */
@Data
public class FileObjectInfo {
    private String name = "";
    private String type = "";
    private Long size;
    private Long time;
    private String pos;
    private String ext = "";
    private String ownerId = "";
    private String ownerName = "";
    private int auth = 0;
}
