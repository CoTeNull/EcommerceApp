package cn.cote.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    @RequiresRoles("admin")
    @GetMapping("/hello")
    public String sayHello(){

//        logger.info("{}====info:{}","Test","子斌");
//        logger.debug("{}===debug:{}","ri","haha");
//        logger.error("{}===error:{}","ri","haha");
//        logger.info("LoggingBack.info(upload)自定义日志输出呢");

        return "Hello";
    }
}
