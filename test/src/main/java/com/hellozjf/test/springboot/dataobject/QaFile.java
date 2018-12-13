package com.hellozjf.test.springboot.dataobject;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Jingfeng Zhou
 */
@Data
@Entity
@Table(name = "QA_FILE")
public class QaFile {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @Column(name="ID")
    private String id;

    @Column(name="FILEID")
    private String fileId;

    @Column(name="GXSJ")
    private Date gxsj;

    @Column(name="HDID")
    private String hdid;

    @Column(name="UPLOADTYPE")
    private String uploadType;

    @Column(name="JHZT")
    private String jhzt;

    @Column(name="FILETYPE")
    private String fileType;

    @Column(name="FILENAME")
    private String fileName;
}
