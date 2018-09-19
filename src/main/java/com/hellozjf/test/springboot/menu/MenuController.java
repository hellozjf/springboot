package com.hellozjf.test.springboot.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jingfeng Zhou
 */
@Controller
public class MenuController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload/upload";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "upload/uploadStatus";
    }
}
