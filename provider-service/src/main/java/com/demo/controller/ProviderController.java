package com.demo.controller;

import com.demo.client.ConsumerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @auther gzhen
 * @date 2023-09-08  15:12
 * @description
 */
@RestController
@RequestMapping("provider")
@Slf4j
public class ProviderController {

    @Resource
    private ConsumerClient consumerClient;

    @GetMapping("/getConsumer")
    public String getConsumer(){
        String port = consumerClient.getPort();
        log.info("provider -> consumer 成功  调用的consumer端口为{}", port);
        return port;
    }

}
