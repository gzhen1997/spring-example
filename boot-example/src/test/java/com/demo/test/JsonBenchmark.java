package com.demo.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JacksonJsonParser;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @auther gzhen
 * @date 2023-10-16  14:43
 * @description
 */
@BenchmarkMode(Mode.AverageTime)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@Threads(2)
@State(Scope.Thread)
public class JsonBenchmark {

    private Gson gson = new Gson();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Param(value = {"{\"startDate\":\"2020-04-01\", \"endDate\":\"2020-05-01\",\"flag\":true,\"thread\":5,\"shardingIndex\":0}","{\"startDate\":\"2020-04-01\", \"endDate\":\"2020-05-01\",\"flag\":true,\"thread\":5,\"shardingIndex\":0}","{\"startDate\":\"2020-04-01\", \"endDate\":\"2020-05-01\",\"flag\":true,\"thread\":5,\"shardingIndex\":0}"})
    String json = "{\"startDate\":\"2020-04-01\", \"endDate\":\"2020-05-01\",\"flag\":true,\"thread\":5,\"shardingIndex\":0}";

    @Benchmark
    public void testGson() {
        gson.fromJson(json, JsonTestModel.class);
    }

    @Test
    public void test() throws RunnerException {
        Options build = new OptionsBuilder()
                .include(this.getClass().getSimpleName())
                .output("d://jmh.log")
                .build();
        new Runner(build).run();
    }

    @Benchmark
    public void testJackson() throws JsonProcessingException, RunnerException {
        objectMapper.readValue(json, JsonTestModel.class);
    }
}

@Data
class JsonTestModel {

    private Date startDate;

    private Date endDate;

    private boolean flag;

    private int shardingIndex;

    private int thread;
}