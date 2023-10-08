package com.demo.event;

/**
 * @auther gzhen
 * @date 2023-09-20  11:39
 * @description
 */

public class LongEvent {

    private long value;

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" + "value=" + value + '}';
    }
}
