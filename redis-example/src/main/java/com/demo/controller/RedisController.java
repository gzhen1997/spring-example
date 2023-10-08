package com.demo.controller;

import com.demo.common.BaseMap;
import com.demo.constant.GlobalConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @auther gzhen
 * @date 2023-10-08  15:29
 * @description
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("add")
    public String add(){
        BaseMap params = new BaseMap();
        params.put(GlobalConstants.HANDLER_NAME, "loderRouderHandler");
        params.put("routerId", null);
        //刷新网关
        redisTemplate.convertAndSend(GlobalConstants.REDIS_TOPIC_NAME_1, params);
        return "SUCCESS";
    }


    @PostMapping("add2")
    public String add2(){
        BaseMap params = new BaseMap();
        params.put(GlobalConstants.HANDLER_NAME, "loderRouderHandler");
        params.put("routerId", null);
        //刷新网关
        redisTemplate.convertAndSend(GlobalConstants.REDIS_TOPIC_NAME_2, params);
        return "SUCCESS";
    }
}
