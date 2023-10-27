package com.demo.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther gzhen
 * @date 2023-10-17  16:38
 * @description
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    @SentinelResource("hello")
    public String apiHello(){
        doBusiness();
        return "SUCCESS!";
    }


    @GetMapping("feignHello")
    public String apiFeignHello() throws BlockException {
        ContextUtil.enter("my_context");
        Entry entry = null;
        try {
            entry = SphU.entry("POST:/HELLO2", EntryType.OUT);
            doBusiness();
            return "Success!";
        }catch (Exception e){
            if (!(e instanceof BlockException)){
                Tracer.trace(e);
            }
            throw e;
        }finally {
            if (entry != null){
                entry.exit();
            }
            ContextUtil.exit();
        }
    }


    private void doBusiness(){
        System.out.println("doBusiness ... ");
    }
}
