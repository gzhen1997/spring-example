package com.demo.security;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;

/**
 * @auther gzhen
 * @date 2023-10-13  11:11
 * @description
 */
@Component
public class ShiroRealm {


    public String doGetAuthenticationInfo(String token){
        System.out.println("doGetAuthenticationInfo");
//        String s = this.checkUserTokenIsEffect(token);
        String s = this.checkUserTokenIsEffect(token);
        return s + ":doGetAuthenticationInfo";
    }


    public String checkUserTokenIsEffect(String token){
        System.out.println("checkUserTokenIsEffect");
        System.out.println("token: " + token);
        return "checkUserTokenIsEffect";
    }
}
