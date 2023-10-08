package com.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther gzhen
 * @date 2023-09-08  10:43
 * @description
 */
@Service
public class SonService extends FatherService{


    public void son(){
        father();
    }

    public void resolveData(List<?> dataList){
        Class<? extends List> aClass = dataList.getClass();
        Object o = dataList.get(0);
    }

}
