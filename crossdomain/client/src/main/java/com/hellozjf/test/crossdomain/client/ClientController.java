package com.hellozjf.test.crossdomain.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Jingfeng Zhou
 */
@Controller
public class ClientController {

    @GetMapping("/index")
    public String logout() {
        return "index";
    }

    @GetMapping("/iframe")
    public String iframe() {
        return "iframe";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView(new RedirectView("http://vultr.hellozjf.com:10111/login?redirectUrl=https://aliyun.hellozjf.com:10112/index"));
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
