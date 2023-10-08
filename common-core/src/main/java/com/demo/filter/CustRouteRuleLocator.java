package com.demo.filter;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;
import com.demo.RouteRule;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @auther gzhen
 * @date 2023-09-08  16:31
 * @description
 */
//@Component
@Data
public class CustRouteRuleLocator {

    private final Gson gson = new Gson();

    public List<RouteRule> routeRules;

    @Autowired
    private NacosConfigProperties nacosConfigProperties;

    public NacosConfigService nacosConfigService;

    @Autowired
    public void routeRuleByNacos() throws NacosException {
        this.nacosConfigService = createNacosConfigService();
        String routeRuleJson = nacosConfigService.getConfig("route-rule.json", "DEFAULT_GROUP", 5000);
        routeRules = gson.fromJson(routeRuleJson, new TypeToken<List<RouteRule>>(){}.getType());
    }

    public NacosConfigService createNacosConfigService() throws NacosException {
        return new NacosConfigService(nacosConfigProperties.assembleConfigServiceProperties());
    }

}
