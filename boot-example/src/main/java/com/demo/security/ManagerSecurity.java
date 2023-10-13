package com.demo.security;

/**
 * @auther gzhen
 * @date 2023-10-13  11:11
 * @description
 */

public class ManagerSecurity {

    ShiroRealm shiroRealm;

    public ManagerSecurity(ShiroRealm shiroRealm) {
        this.shiroRealm = shiroRealm;
    }

    public String doAuth(String token){
        return shiroRealm.doGetAuthenticationInfo(token);
    }
}
