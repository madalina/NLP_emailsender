package com.madi.nlp.emailsender;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AliveSender implements Job{
    private static final Logger log = LoggerFactory
            .getLogger(AliveSender.class);

    public static void main(String[] args) {
        AliveSender nru = new AliveSender();
        try {
            nru.execute(null);
        } catch (JobExecutionException e) {
            log.error(e.getMessage());
        }
    }

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        log.debug("alive at " + new Date());
    }
}
