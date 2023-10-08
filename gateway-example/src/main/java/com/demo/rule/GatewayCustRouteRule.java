package com.demo.rule;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.demo.filter.CustGlobalFilter;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @auther gzhen
 * @date 2023-09-08  17:35
 * @description
 */
public class GatewayCustRouteRule extends RoundRobinRule {

    @Autowired
    private CustGlobalFilter custGlobalFilter;

    @Override
    public Server choose(ILoadBalancer lb, Object key) {
        List<Server> allServers = lb.getAllServers();
        String zone = custGlobalFilter.getZoneThreadLocal().get();
        try {
            if (StringUtils.isNotBlank(zone)) {
                for (Server server : allServers) {
                    NacosServer nacosServer = (NacosServer) server;
                    Map<String, String> metadata = nacosServer.getMetadata();
                    if (metadata != null && metadata.size() > 0) {
                        if (zone.equals(metadata.get("zone"))) {
                            return server;
                        }
                    }
                }
            }
        }finally {
            custGlobalFilter.removeThreadLocal();
        }
        return super.choose(lb, key);
    }
}
