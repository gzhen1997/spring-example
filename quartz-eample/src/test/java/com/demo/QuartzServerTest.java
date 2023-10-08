package com.demo;

import com.demo.job.MyJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @auther gzhen
 * @date 2023-09-12  11:33
 * @description
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class QuartzServerTest {

    @Resource
    private Scheduler scheduler;


    @Test
    public void addJob() throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobParam", "新增任务");
        JobDetail job = JobBuilder.newJob()
                .withIdentity("job1", "group1")
                .setJobData(jobDataMap)
                .ofType(MyJob.class)
                .build();

        JobDataMap triggerDataMap = new JobDataMap();
        jobDataMap.put("TriggerParam", "触发器参数");
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .forJob(job)
                .usingJobData(triggerDataMap)
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(6) //每隔3秒执行一次
                        .withRepeatCount(6))
                .build();

        scheduler.scheduleJob(job, trigger);
//        scheduler.start();
    }
}
