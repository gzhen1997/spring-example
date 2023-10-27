package com.demo.sentinel.test;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @auther gzhen
 * @date 2023-10-18  17:05
 * @description
 */

public class AtomicLongTest {

    @Test
    public void testAtomicLong(){
        AtomicLong latestPassTime = new AtomicLong(-1);
        long l = latestPassTime.addAndGet(2);
        System.out.println(l);

    }

}
