package com.demo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @auther gzhen
 * @date 2023-09-11  11:11
 * @description
 */
@Component
public class MyJob implements Job {

    @Autowired
    private Environment environment;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(environment + "=======================");
        Object tv1 = jobExecutionContext.getTrigger().getJobDataMap().get("trigger1");
        Object tv2 = jobExecutionContext.getTrigger().getJobDataMap().get("trigger2");
        Object jv1 = jobExecutionContext.getJobDetail().getJobDataMap().get("jobDetail1");
        Object jv2 = jobExecutionContext.getJobDetail().getJobDataMap().get("jobDetail2");
        Object sv = null;
        try {
            sv = jobExecutionContext.getScheduler().getContext().get("scheduler");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        System.out.println(sv);
        System.out.println(tv1+":"+tv2);
        System.out.println(jv1+":"+jv2);
        System.out.println(Thread.currentThread().getName() + "--"
                + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
    }
}