package com.hellozjf.test.springboot.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jingfeng Zhou
 */
@Controller
public class MenuController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload/upload";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "upload/uploadStatus";
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/uri")
    public String uri() {
        return "uri/uri";
    }

    @GetMapping("/helloObject")
    public String hello() {
        return "helloObject/helloObject";
    }

    @GetMapping("/ajax")
    public String ajax() {
        return "ajax/ajax";
    }

    @GetMapping("/promise")
    public String promise() {
        return "promise/promise";
    }

    @GetMapping("/session")
    public String session() {
        return "session/session";
    }

    @GetMapping("/request")
    public String request() {
        return "session/request";
    }
}
