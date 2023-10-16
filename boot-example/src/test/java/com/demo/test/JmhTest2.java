package com.demo.test;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @auther gzhen
 * @date 2023-10-16  14:40
 * @description
 */
@BenchmarkMode(Mode.AverageTime)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1 , timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@Threads(2)
public class JmhTest2 {

    // idea安装 jmh plugin
    @Benchmark
    public void testJmh() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("jmh");
    }

    @Benchmark
    public void testJmh2() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("jmh2");
    }
}
