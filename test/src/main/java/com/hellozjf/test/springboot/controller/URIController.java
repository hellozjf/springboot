package com.hellozjf.test.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jingfeng Zhou
 */
@RestController
@RequestMapping("/uri")
@Slf4j
public class URIController {

    @GetMapping("/get")
    public String get(String en, String cn, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("en=");
        sb.append(en);
        sb.append(", cn=");
        sb.append(cn);
        log.debug("{}", sb.toString());

        String parameterEn = request.getParameter("en");
        String parameterCn = request.getParameter("cn");
        log.debug("getParameter en={} cn={}", en, cn);

        return sb.toString();
    }
}
