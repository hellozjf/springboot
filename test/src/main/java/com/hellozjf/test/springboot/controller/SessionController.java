package com.hellozjf.test.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Jingfeng Zhou
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @PostMapping("/setAttr")
    public void setAttr(HttpServletRequest request, String key, String value) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(key, value);
    }

    @PostMapping("/setRequestAttr")
    public void setRequestAttr(HttpServletRequest request, String key, String value) {
        request.setAttribute(key, value);
    }

    @GetMapping("/getAttr")
    public Object getAttr(HttpServletRequest request, String key) {
        HttpSession httpSession = request.getSession();
        return httpSession.getAttribute(key);
    }

    @GetMapping("/getRequestAttr")
    public Object getRequestAttr(HttpServletRequest request, String key) {
        return request.getAttribute(key);
    }
}
