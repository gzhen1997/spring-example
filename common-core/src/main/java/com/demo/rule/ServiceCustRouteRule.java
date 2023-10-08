package com.demo.rule;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @auther gzhen
 * @date 2023-09-08  17:52
 * @description
 */
//@Component
public class ServiceCustRouteRule extends RoundRobinRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;


    @Override
    public Server choose(ILoadBalancer lb, Object key) {
        Map<String, String> metadata = nacosDiscoveryProperties.getMetadata();
        if (metadata != null && metadata.size() > 0 ){
            String zone = metadata.get("zone");
            List<Server> allServers = lb.getAllServers();
            for (Server server : allServers) {
                NacosServer nacosServer = (NacosServer) server;
                Map<String, String> nextMetadata = nacosServer.getMetadata();
                if (StringUtils.isNotBlank(zone) && nextMetadata != null && zone.equals(nextMetadata.get("zone"))){
                    return nacosServer;
                }
            }
        }
        return super.choose(lb, key);
    }
}
