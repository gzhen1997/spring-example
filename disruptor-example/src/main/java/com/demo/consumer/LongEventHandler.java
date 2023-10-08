package com.demo.consumer;

import com.demo.event.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * @auther gzhen
 * @date 2023-09-20  11:40
 * @description
 */

public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("longEvent:" + longEvent + "\t sequence:" + sequence + "\t endOfBatch:" + endOfBatch);
        System.out.println("currentThread:" + Thread.currentThread().getName() + " Event: " + longEvent);
    }
}