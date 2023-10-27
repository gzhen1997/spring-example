package com.demo.sentinel.test;

import org.junit.jupiter.api.Test;

/**
 * @auther gzhen
 * @date 2023-10-17  15:15
 * @description
 */

public class MenuTest {

    @Test
    public void testEnum(){
        MetricType[] metricTypes = MetricType.values();
        for (MetricType metricType : metricTypes) {
            System.out.println(metricType + ":" + metricType.ordinal());
        }
    }


    @Test
    public void testMod(){
        int count = 12349167;
        int mod = 60;
        int timeMills = count - (count % 100);
        int idx = timeMills % mod;
        System.out.println(idx);
    }
}






enum MetricType{
    PASS,
    BLOCK,
    EXCEPTION,
    SUCCESS,
    RT
    ;
}
