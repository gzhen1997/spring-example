package com.demo.receiver;

import cn.hutool.core.util.ObjectUtil;
import com.demo.common.BaseMap;
import com.demo.constant.GlobalConstants;
import com.demo.listener.ClmpRedisListener;
import com.demo.utils.SpringContextHolder;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @auther gzhen
 * @date 2023-10-08  14:18
 * @description
 */
@Component
@Data
public class RedisReceiver {


    /**
     * 接受消息并调用业务逻辑处理器
     *
     * @param params
     */
    public void onMessage(BaseMap params) {
        System.out.println("onMessage方法执行");
        Object handlerName = params.get(GlobalConstants.HANDLER_NAME);
        ClmpRedisListener messageListener = SpringContextHolder.getHandler(handlerName.toString(), ClmpRedisListener.class);
        if (ObjectUtil.isNotEmpty(messageListener)) {
            messageListener.onMessage(params);
        }
    }

    /**
     * 接受消息并调用业务逻辑处理器
     *
     * @param params
     */
    public void onMessage2(BaseMap params) {
        System.out.println("onMessage2方法执行");
    }

}
