package com.demo.disruptor;

import com.demo.consumer.CommonEventHandler;
import com.demo.consumer.LongEventHandler;
import com.demo.event.LongEvent;
import com.demo.factory.LongEventFactory;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @auther gzhen
 * @date 2023-09-20  11:41
 * @description
 */

public class MyTest {

    @Test
    public void test() throws InterruptedException {
        int bufferSize = 2;
        Disruptor<LongEvent> disruptor =
                new Disruptor<>(
                        new LongEventFactory(),
                        bufferSize,
                        DaemonThreadFactory.INSTANCE,
                        ProducerType.SINGLE,
                        new BlockingWaitStrategy());
        disruptor.handleEventsWith(new LongEventHandler(), new CommonEventHandler());
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
            Thread.sleep(1000);
        }
    }
}
