package com.demo.factory;

import com.demo.event.LongEvent;
import com.lmax.disruptor.EventFactory;

/**
 * @auther gzhen
 * @date 2023-09-20  11:39
 * @description
 */

public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}