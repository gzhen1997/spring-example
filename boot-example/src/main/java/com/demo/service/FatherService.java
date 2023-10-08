package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @auther gzhen
 * @date 2023-09-08  10:43
 * @description
 */

public class FatherService {

    @Autowired
    private ApplicationContext applicationContext;

    public void father(){
        System.out.println(applicationContext);
    }
}
