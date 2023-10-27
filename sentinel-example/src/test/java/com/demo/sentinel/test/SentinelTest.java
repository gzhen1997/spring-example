package com.demo.sentinel.test;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.context.ContextUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @auther gzhen
 * @date 2023-10-17  14:24
 * @description
 */

public class SentinelTest {


    @Test
    public void sentinelTest(){
        ContextUtil.enter("demo");
        Entry entry = null;
        try {
            entry = SphU.entry("resource_name", EntryType.IN);
            doBusiness();
        }catch (Exception e){
            Tracer.trace(e);
        }finally {
            if (entry != null){
                entry.exit();
            }
            ContextUtil.exit();
        }
    }


    public void doBusiness() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("doBusiness");
    }

    @Test
    public void testMod(){
        long timeMilli = System.currentTimeMillis();
        int lengthInWindows = 500;
        int arrayLength = 2;
        int idx = (int)((timeMilli / lengthInWindows) % arrayLength);
        long startTime = timeMilli - timeMilli % lengthInWindows;
        System.out.println("idx:" + idx);
        System.out.println("timeMilli:" + timeMilli);
        System.out.println("startTime:" + startTime);

    }
}
