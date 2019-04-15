package com.hellozjf.test.springboot.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellozjf.test.springboot.config.FileConfig;
import com.hellozjf.test.springboot.domain.HelloObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@Controller
@Slf4j
public class FileController {

    private static final String UPLOADED_FOLDER = "C:\\";

    @Autowired
    @Qualifier("helloObjectList")
    private List<HelloObject> helloObjectList;

    @Autowired
    private FileConfig fileConfig;

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws Exception {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        // 我还需要从文件中获取信息，并将信息写入到helloObjectList中
        try {
            byte[] bytes = file.getBytes();
            ObjectMapper objectMapper = new ObjectMapper();
            List<HelloObject> list = objectMapper.readValue(bytes, new TypeReference<List<HelloObject>>(){});
            helloObjectList.clear();
            helloObjectList.addAll(list);
            log.debug("clear helloObjectList and add {}", list);
        } catch (IOException e) {
            log.error("发生异常：{}", e);
        }

        if (fileConfig.isWriteToFile()) {
            // 这里是写入文件
            try {
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 这里addFlashAttribute，表示这个属性仅在跳转第一次的时候生效，跳转完成之后失效
        // 具体请参见https://www.cnblogs.com/geekdc/p/5787793.html
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + file.getOriginalFilename() + "'");

        return "redirect:/uploadStatus";
    }
}
