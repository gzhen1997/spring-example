package com.demo.config;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @auther gzhen
 * @date 2023-09-11  14:45
 * @description 调度器配置类
 */
@Configuration
@AutoConfigureAfter(value = QuartzAutoConfiguration.class)
public class SchedulerConfig {

    /**
     * 读取quartz.properties,将值初始化
     *
     * @return Properties
     * @throws IOException io
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /**
     * 将配置文件的数据加载到SchedulerFactoryBean中
     *
     * @return SchedulerFactoryBean
     * @throws IOException io
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource  dataSource,
                                                     ApplicationContext applicationContext) throws Exception {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setSchedulerName("mySchedule");
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        return schedulerFactoryBean;
    }
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean() throws Exception {
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
//        schedulerFactoryBean.setJobFactory(jobFactory);
//        schedulerFactoryBean.setSchedulerName("mySchedule2");
//        schedulerFactoryBean.setQuartzProperties(quartzProperties());
//        return schedulerFactoryBean;
//    }

    /**
     * 初始化监听器  初始化监听器时机不能过早
     *
     * @return QuartzInitializerListener
     */
//    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    /**
     * 获得Scheduler对象
     *
     * @return Scheduler
     * @throws IOException io
     */
    @Bean
    public Scheduler scheduler() throws Exception {
        return schedulerFactoryBean(null, null).getScheduler();
//        return schedulerFactoryBean().getScheduler();
    }
}