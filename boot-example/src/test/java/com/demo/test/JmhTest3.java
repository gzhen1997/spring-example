package com.demo.test;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

/**
 * @auther gzhen
 * @date 2023-10-16  15:27
 * @description
 */

public class JmhTest3 {

    @Test
    public void test() throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JsonBenchmark.class.getSimpleName())
                .forks(1)
                .threads(2)
                .timeUnit(TimeUnit.MILLISECONDS)
                .warmupIterations(1)
                .warmupTime(TimeValue.seconds(1))
                .measurementTime(TimeValue.seconds(1))
                .measurementIterations(5)
                .mode(Mode.AverageTime)
                .build();
        new Runner(options).run();
    }
}
