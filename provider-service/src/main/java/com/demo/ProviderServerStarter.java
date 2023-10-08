package com.demo;

import com.demo.filter.ServiceRibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @auther gzhen
 * @date 2023-09-08  14:48
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.demo.client")
@RibbonClients(defaultConfiguration = ServiceRibbonConfig.class)
public class ProviderServerStarter {
    public static void main(String[] args){
        SpringApplication.run(ProviderServerStarter.class, args);
    }
}
