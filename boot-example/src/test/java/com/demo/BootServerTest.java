package com.demo;

import com.demo.security.LoginFilter;
import com.demo.security.ManagerSecurity;
import com.demo.security.ShiroRealm;
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


    @Autowired
    private ManagerSecurity managerSecurity;

    @Autowired
    private ShiroRealm shiroRealm;

    @Autowired
    private LoginFilter loginFilter;

    @Test
    public void testAop(){
        managerSecurity.doAuth("gz");
    }

    @Test
    public void testAop2(){
        shiroRealm.checkUserTokenIsEffect("gz");
    }

    @Test
    public void testAop3(){
        loginFilter.loginFilter("gz");
    }


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


