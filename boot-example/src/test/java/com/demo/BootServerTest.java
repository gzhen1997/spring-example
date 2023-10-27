package com.demo;

import com.demo.security.LoginFilter;
import com.demo.security.ManagerSecurity;
import com.demo.security.ShiroRealm;
import com.demo.service.FatherService;
import com.demo.service.SonService;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

@Configuration
class PersonConfig{

    @Autowired
    private void map(ObjectProvider<Person[]> objectProvider){
        System.out.println(objectProvider);
    }
}

interface Person{
    void eat();
}

@Component
class Man implements Person{

    @Override
    public void eat() {
        System.out.println("man :: eat");
    }

}
@Component
class Women implements Person{

    @Override
    public void eat() {
        System.out.println("Women :: eat");
    }
}
