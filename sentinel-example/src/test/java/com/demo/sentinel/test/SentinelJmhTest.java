package com.demo.sentinel.test;

import com.alibaba.csp.sentinel.SphU;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import com.alibaba.csp.sentinel.Entry;

/**
 * @auther gzhen
 * @date 2023-10-16  16:06
 * @description
 */
@Fork(1)
@Warmup(iterations = 10)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class SentinelJmhTest {

    @Param({"25", "50", "100", "200", "500", "1000"})
    private int length;

    private List<Integer> numbers;

    @Setup
    public void init(){
        numbers = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            numbers.add(ThreadLocalRandom.current().nextInt());
        }
    }

    @Benchmark
    @Threads(8)
    public void doSomething(){
        Collections.shuffle(numbers);
        Collections.sort(numbers);
    }


    @Benchmark
    @Threads(8)
    public void doSomethingWithEntry(){
        Entry e0 = null;
        try {
            e0 = SphU.entry("benchmark");
            doSomething();
        }catch (Exception e){

        }finally {
            if (e0 != null) {
                e0.exit();
            }
        }
    }
}
