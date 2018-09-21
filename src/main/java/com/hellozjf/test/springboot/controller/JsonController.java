package com.hellozjf.test.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellozjf.test.springboot.vo.HelloObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@RestController
@Slf4j
public class JsonController {

    @Autowired
    private HelloObject helloObject;

    @Autowired
    @Qualifier("helloObjectList")
    List<HelloObject> helloObjectList;

    @GetMapping("/getHelloObject")
    public HelloObject getHelloObject() {
        return helloObject;
    }

    @GetMapping("/getHelloObjectList")
    public List<HelloObject> getHelloObjectList() {
        return helloObjectList;
    }

    @GetMapping("/getJsonFile")
    public void getJsonFile(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=download.json");
        try (OutputStream out = response.getOutputStream()) {
            writeObjectToOutputStream(helloObject, out);
        } catch (IOException e) {
            log.error("发送文件发生异常：{}", e);
        }
    }

    @GetMapping("/getJsonListFile")
    public void getJsonListFile(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=downloadList.json");
        try (OutputStream out = response.getOutputStream()) {
            writeObjectToOutputStream(helloObjectList, out);
        } catch (IOException e) {
            log.error("发送文件发生异常：{}", e);
        }
    }

    private void writeObjectToOutputStream(Object object, OutputStream out) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(object);
        out.write(jsonString.getBytes());
        out.flush();
    }
}
