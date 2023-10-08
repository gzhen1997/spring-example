package com.demo;

import com.demo.filter.ServiceRibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * @auther gzhen
 * @date 2023-09-08  14:47
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@RibbonClients(defaultConfiguration = ServiceRibbonConfig.class)
public class ConsumerServerStarter {
    public static void main(String[] args){
        SpringApplication.run(ConsumerServerStarter.class, args);
    }
}
