package com.hellozjf.test.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.hellozjf.test.springboot.constant.ResultEnum;
import com.hellozjf.test.springboot.domain.FileObject;
import com.hellozjf.test.springboot.domain.QaFile;
import com.hellozjf.test.springboot.repository.QaFileRepository;
import com.hellozjf.test.springboot.util.QaFileUtils;
import com.hellozjf.test.springboot.util.ResultUtils;
import com.hellozjf.test.springboot.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@RestController
@Slf4j
public class QaFileController {

    @Autowired
    private QaFileRepository qaFileRepository;

    /**
     * 将配置文件summary_7d0aaafd47ba40869e1f464e70589f3c.json内容导入到公司的Oracle数据库中
     * @return
     */
    @GetMapping("/importQaFiles")
    public ResultVO importQaFiles() {

//        List<QaFile> qaFileList = qaFileRepository.findAll();
//        return ResultUtils.success(qaFileList);

        // 先清除qaFileList
        qaFileRepository.deleteAll();

        ClassPathResource classPathResource = new ClassPathResource("static/json/summary_7d0aaafd47ba40869e1f464e70589f3c.json");
        String filename = classPathResource.getFilename();
        log.debug("filename = {}", filename);

        try (
                InputStream in = classPathResource.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            List<FileObject> fileObjectList = JSON.parseArray(stringBuilder.toString(), FileObject.class);
            List<QaFile> qaFileList = QaFileUtils.getQaFileListByFileObjectList(fileObjectList);
            qaFileRepository.saveAll(qaFileList);

            return ResultUtils.success(fileObjectList);
        } catch (Exception e) {
            log.error("error: {}", e);
            return ResultUtils.error(ResultEnum.FILE_ERROR.getCode(), e.getMessage());
        }
    }
}
