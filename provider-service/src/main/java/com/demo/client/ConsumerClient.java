package com.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther gzhen
 * @date 2023-09-08  15:14
 * consumer-service
 * @description
 */
@FeignClient(value = "consumer-service")
public interface ConsumerClient {

    @GetMapping("/consumer/port")
    String getPort();
}
