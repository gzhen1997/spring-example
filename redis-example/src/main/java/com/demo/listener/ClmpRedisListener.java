package com.demo.listener;

import com.demo.common.BaseMap;

/**
 * @auther gzhen
 * @date 2023-10-08  14:18
 * @description
 */

public interface ClmpRedisListener {

    void onMessage(BaseMap message);
}
