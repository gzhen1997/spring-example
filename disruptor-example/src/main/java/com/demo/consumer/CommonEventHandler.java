package com.demo.consumer;

import com.demo.event.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * @auther gzhen
 * @date 2023-10-07  16:50
 * @description
 */

public class CommonEventHandler implements EventHandler<Object> {
    @Override
    public void onEvent(Object event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("common\t " + "longEvent:" + event + "\t sequence:" + sequence + "\t endOfBatch:" + endOfBatch);
    }
}
