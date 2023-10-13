package com.demo.security.plugin;

import com.demo.security.ShiroRealm;

/**
 * @auther gzhen
 * @date 2023-10-13  11:30
 * @description
 */

public class TestShiroRealm extends ShiroRealm {

    @Override
    public String checkUserTokenIsEffect(String token) {

        if ("jzr".equals(token)){
            System.out.println("杰智融");
        }
        return token;
    }
}
