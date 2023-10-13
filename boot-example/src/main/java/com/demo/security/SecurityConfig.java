package com.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther gzhen
 * @date 2023-10-13  11:14
 * @description
 */
@Configuration
public class SecurityConfig {

    @Bean
    public ManagerSecurity managerSecurity(ShiroRealm shiroRealm) {
        return new ManagerSecurity(shiroRealm);
    }
}
