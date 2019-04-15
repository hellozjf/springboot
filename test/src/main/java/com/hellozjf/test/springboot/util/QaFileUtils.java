package com.hellozjf.test.springboot.util;

import com.hellozjf.test.springboot.domain.FileObject;
import com.hellozjf.test.springboot.domain.QaFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jingfeng Zhou
 */
public class QaFileUtils {
    public static QaFile getQaFileByFileObject(FileObject fileObject) {
        QaFile qaFile = new QaFile();
        qaFile.setFileId(fileObject.getOid());
        qaFile.setFileName(fileObject.getName());
        qaFile.setFileType(fileObject.getType());
        qaFile.setJhzt("0");
        qaFile.setGxsj(new Date(fileObject.getTime()));
        return qaFile;
    }

    public static List<QaFile> getQaFileListByFileObjectList(List<FileObject> fileObjectList) {
        List<QaFile> qaFileList = new ArrayList<>();
        for (FileObject fileObject : fileObjectList) {
            qaFileList.add(getQaFileByFileObject(fileObject));
        }
        return qaFileList;
    }
}
