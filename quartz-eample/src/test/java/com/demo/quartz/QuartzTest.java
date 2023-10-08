package com.demo.quartz;

import com.demo.job.MyJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @auther gzhen
 * @date 2023-09-11  11:12
 * @description
 */

public class QuartzTest {

    @Test
    public void jobTest() throws SchedulerException, InterruptedException {

        //1、创建一个scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().put("scheduler", "生成scheduler!");

        //2、创建JobDetail实例，并与MyJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .usingJobData("jobDetail1", "这是jobDetail第一个参数值！")
                .withIdentity("myjob", "jobGroup")
                .build();

        //3、构建Trigger（触发器），定义执行频率和时长
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "triggerGroup")
                .usingJobData("trigger1", "这是trigger第一个参数值！")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3) //每隔3秒执行一次
                        .repeatForever() //永久执行
                ).build();

        //4、往jobDetail的dataMap里存放键值对
        jobDetail.getJobDataMap().put("jobDetail2", "这是jobDetail第二个参数值！");

        //5、往trigger的dataMap里存放键值对
        trigger.getJobDataMap().put("trigger2", "这是trigger第二个参数值！");

        //6、组装jobDetail和trigger，交由scheduler调度
        scheduler.scheduleJob(jobDetail, trigger);

        //7、启动scheduler
        scheduler.start();

        //8、休眠，决定调度器运行时间，这里设置30s
        TimeUnit.SECONDS.sleep(30);

        //9、关闭scheduler
        scheduler.shutdown();

    }

}