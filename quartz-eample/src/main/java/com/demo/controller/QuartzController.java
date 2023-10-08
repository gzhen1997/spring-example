package com.demo.controller;

import com.demo.job.MyJob;
import org.quartz.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * @auther gzhen
 * @date 2023-09-12  14:50
 * @description
 */
@RestController
@RequestMapping("quartz")
public class QuartzController {

    @Resource
    private Scheduler scheduler;

    @PostMapping("add")
    public String add() throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobDetail1", "新增任务参数1");
        jobDataMap.put("jobDetail2", "新增任务参数2");
        JobDetail job = JobBuilder.newJob()
                .withIdentity("job1", "group1")
                .setJobData(jobDataMap)
                .ofType(MyJob.class)
                .build();

        JobDataMap triggerDataMap = new JobDataMap();
        jobDataMap.put("trigger1", "触发器参数1");
        jobDataMap.put("trigger2", "触发器参数1");
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .forJob(job)
                .usingJobData(triggerDataMap)
                .withIdentity("trigger1", "group1")
                .startAt(getDate())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(6) //每隔3秒执行一次
                        .withRepeatCount(6))
                .build();

        scheduler.scheduleJob(job, trigger);
        return "增加成功！";
    }


    public Date getDate(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MINUTE, 5);
        Date time = cal.getTime();
        return time;
    }



}
