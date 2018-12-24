package com.hellozjf.test.crossdomain.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Jingfeng Zhou
 */
@Controller
@Slf4j
public class ServerController {

    @GetMapping("/login")
    public ModelAndView login(String redirectUrl) {
        log.info("login!");
        CookieUtils.addCookie("login", "1");
        return new ModelAndView(new RedirectView(redirectUrl));
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout() {
        log.info("logout!");
        CookieUtils.removeCookie("login");
        return "logout!";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        log.info("hello!");
        return "hello!";
    }
}
