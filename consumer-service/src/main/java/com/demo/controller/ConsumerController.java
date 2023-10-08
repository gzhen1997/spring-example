package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther gzhen
 * @date 2023-09-08  14:53
 * @description
 */
@RestController
@RequestMapping("/consumer")
@Slf4j
public class ConsumerController {

    @Value("${server.port}")
    public String port;

    @GetMapping("/port")
    public String getPort(){
        log.info("consumer-service服务端口为{}", port);
        return port;
    }
}
