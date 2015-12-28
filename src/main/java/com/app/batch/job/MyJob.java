package com.app.batch.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.batch.service.MyService;
import com.google.inject.Inject;

public class MyJob implements Job{
	
	public static final String JOB_NAME = "MYJOB";
	public static final String JOB_GROUP = "MYJOBGROUP";
	public static final String TRIGGER_NAME = "MYTRIGGER";
	public static final String TRIGGER_GROUP = "MYTRIGGERGROUP";
	public static final String CRON_KEY = "cron.job.myjob";

	private static final Logger logger = LoggerFactory.getLogger(MyJob.class);
	
	@Inject
	private MyService myService;
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("Start running job [{}]", JOB_NAME);
		myService.printCurrentTime();
		logger.info("Job [{}] finished successfully...",JOB_NAME);
	}

	
}
