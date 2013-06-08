package com.madi.nlp.emailsender;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzScheduler {
    public static void main(String[] args) throws InterruptedException {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();
            
            Logger.getLogger(QuartzScheduler.class).info("Started the quartz scheduler at " + new Date());
            
            JobDetail emailJob = newJob(NoteReminderUpdater.class).withIdentity(
                    "job1", "group1").build();

            Trigger triggerEmailSender = newTrigger()
                    .withIdentity("trigger_emails", "group_emails").startNow()
                    .withSchedule(dailyAtHourAndMinute(04, 00))
                    .build();
            
            
            JobDetail aliveJob = newJob(AliveSender.class).withIdentity(
                    "job2", "group2").build();
            Trigger triggerAlive = newTrigger()
                    .withIdentity("trigger_alive", "group_alive").startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/2 * * ?")).build();

            
            scheduler.scheduleJob(emailJob, triggerEmailSender);
            scheduler.scheduleJob(aliveJob, triggerAlive);
            
            Logger.getLogger(QuartzScheduler.class).info("Scheduled the job NoteReminderUpdater and AliveSender");
            
            
            

        } catch (SchedulerException se) {
            Logger.getLogger(QuartzScheduler.class).error(se.getMessage());
        }
    }
}
