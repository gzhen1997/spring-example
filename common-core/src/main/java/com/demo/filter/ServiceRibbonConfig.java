package com.demo.filter;

import com.demo.rule.ServiceCustRouteRule;
import org.springframework.context.annotation.Bean;

/**
*   @auther gzhen
*   @date   2023-09-08  18:28 
*   @description 
*/

public class ServiceRibbonConfig {

    @Bean
    public ServiceCustRouteRule serviceCustRouteRule(){
        return new ServiceCustRouteRule();
    }

}
