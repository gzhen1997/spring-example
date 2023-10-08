package com.demo.listener;

import com.demo.common.BaseMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @auther gzhen
 * @date 2023-10-08  15:34
 * @description
 */
@Slf4j
@Component
public class LoderRouderHandler implements ClmpRedisListener {

    @Override
    public void onMessage(BaseMap message) {
        System.out.println("BaseMap:" + message);
    }

}