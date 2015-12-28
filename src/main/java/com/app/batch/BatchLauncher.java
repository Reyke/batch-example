package com.app.batch;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.batch.GuiceJobFactory;
import com.app.batch.job.MyJob;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class BatchLauncher {
	
	private static final Logger logger = LoggerFactory.getLogger(MyJob.class);
	
	private final Scheduler scheduler;

	private volatile boolean allStarted = false;

	public Scheduler getScheduler() {
		return scheduler;
	}
	
	@Inject
	public BatchLauncher(final SchedulerFactory factory,
	final GuiceJobFactory jobFactory) throws SchedulerException {
		scheduler = factory.getScheduler();
		scheduler.setJobFactory(jobFactory);
	}
	
	public void start() throws SchedulerException {
		scheduler.start();
	}

	public void shutdown() throws SchedulerException {
		scheduler.shutdown();
	}

	private void schedule(JobDetail job, Trigger trigger)
			throws SchedulerException {
		scheduler.scheduleJob(job, trigger);
	}
	
	public synchronized void executeJobs(){
	try {
		if (!allStarted) {
			start();
			schedule(myJob,myTrigger);
			allStarted = true;
			
			logger.info("all jobs have been scheduled.");
		}
	} catch (SchedulerException e) {
		throw new RuntimeException(
				"start up failed.", e);
	}
}
	
	@Inject
	@Named(MyJob.JOB_NAME)
	private JobDetail myJob;
	
	@Inject
	@Named(MyJob.TRIGGER_NAME)
	private Trigger myTrigger;

}
