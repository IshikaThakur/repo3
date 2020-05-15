package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.entities.JobModel;
import com.ecommerceApp.ecommerceApp.scheduler.ProductScheduler;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JobController {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ApplicationContext context;;

    @ResponseBody
    @RequestMapping(value = "admin/schedule/report/making", method = RequestMethod.POST)
    public ResponseEntity<JobModel> dailyJob(@RequestBody JobModel jobModel) throws SchedulerException {
        JobDetail jobDetail = context.getBean(
                JobDetail.class, jobModel.getName(), "MyDailyJob", ProductScheduler.class);
       Trigger cronTrigger = context.<Trigger>getBean(
               Trigger.class, jobModel.getCronExpression(), "MyDailyJob");

        scheduler.scheduleJob(jobDetail, cronTrigger);

        return new ResponseEntity<JobModel>(jobModel, HttpStatus.CREATED);
    }
}