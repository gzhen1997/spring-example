package com.demo;

import com.demo.service.FatherService;
import com.demo.service.SonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther gzhen
 * @date 2023-09-08  10:44
 * @description
 */
@SpringBootTest
public class BootServerTest {

    @Autowired
    private SonService service;


    @Test
    public void test(){
        service.son();
    }

    @Test
    public void test2(){
        List<SonService> sonServices = new ArrayList<>();
        boolean add = sonServices.add(service);
        service.resolveData(sonServices);
    }


}


