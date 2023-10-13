package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auther gzhen
 * @date 2023-10-13  11:27
 * @description
 */
@Component
public class LoginFilter {

    @Autowired
    private ManagerSecurity managerSecurity;

    public String loginFilter(String token){
        String s = managerSecurity.doAuth(token);
        return s;
    }
}
