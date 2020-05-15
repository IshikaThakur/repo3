package com.ecommerceApp.ecommerceApp.scheduler;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableScheduling
public class QuartzConfig {

    @Autowired
    ApplicationContext context;

    @Bean
    public SchedulerFactoryBean quartzScheduler(){
        SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
        quartzScheduler.setOverwriteExistingJobs(true);
        quartzScheduler.setSchedulerName("job-scheduler");
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(context);
        quartzScheduler.setJobFactory(jobFactory);
        return quartzScheduler;
    }

    @Bean
    @Scope(value = "prototype")
    public JobDetailFactoryBean getJobBean(String jobName, String jobGroup, Class<?> clazz){
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass((Class<? extends Job>) clazz);
        bean.setGroup(jobGroup);
        bean.setName(jobName);
        return bean;
    }

    @Bean
    @Scope(value = "prototype")
    public CronTriggerFactoryBean getCronTriggerBean(String cronExpression, String triggerGroup){
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression(cronExpression);
        bean.setGroup(triggerGroup);
        return bean;
    }
}