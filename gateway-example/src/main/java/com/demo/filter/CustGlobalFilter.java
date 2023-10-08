package com.demo.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.stream.Stream;

/**
 * @auther gzhen
 * @date 2023-09-08  17:15
 * @description
 */
@Component
public class CustGlobalFilter implements GlobalFilter , Ordered {

    public static final int CUST_GLOBAL_FILTER_ORDER = 10099;

    public ThreadLocal<String> zoneThreadLocal = new NamedThreadLocal<>("zone信息");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI uri = exchange.getRequest().getURI();
        String query = uri.getQuery();
        if (StringUtils.isNotBlank(query)){
            String[] split = query.split("&");
            Stream.of(split).forEach(param -> {
                String[] meta = param.split("=");
                String key = meta[0];
                String val = meta[1];
                if ("zone".equals(key)){
                    zoneThreadLocal.set(val);
                }
            });
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return CUST_GLOBAL_FILTER_ORDER;
    }

    public ThreadLocal<String> getZoneThreadLocal() {
        return zoneThreadLocal;
    }

    public void setZoneThreadLocal(ThreadLocal<String> zoneThreadLocal) {
        this.zoneThreadLocal = zoneThreadLocal;
    }

    public void removeThreadLocal() {
        this.zoneThreadLocal.remove();
    }
}
