package com.demo.test;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @auther gzhen
 * @date 2023-10-16  14:32
 * @description
 */

public class JmhTest {

    @BenchmarkMode(Mode.AverageTime)
    @Benchmark
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @Warmup(iterations = 5, time = 1 , timeUnit = TimeUnit.SECONDS)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(2)
    @Test
    public void testJmh() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("jmh");
    }
}
