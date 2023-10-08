package com.demo.filter;

import com.demo.rule.GatewayCustRouteRule;
import org.springframework.context.annotation.Bean;

/**
 * @auther gzhen
 * @date 2023-09-11  10:05
 * @description
 */

public class GatewayRibbonConfig {

    @Bean
    public GatewayCustRouteRule gatewayCustRouteRule(){
        return  new GatewayCustRouteRule();
    }
}
