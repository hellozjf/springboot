package com.hellozjf.test.springboot.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hellozjf.test.springboot.dubbo.service.DemoService;

/**
 * @author Jingfeng Zhou
 */
@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DefaultDemoService implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }
}
