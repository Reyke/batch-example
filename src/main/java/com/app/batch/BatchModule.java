package com.app.batch;

import org.apache.commons.configuration.Configuration;
import org.quartz.JobDetail;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

import com.app.batch.job.MyJob;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.name.Named;

public class BatchModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(SchedulerFactory.class).to(StdSchedulerFactory.class).in(
				Scopes.SINGLETON);
		bind(GuiceJobFactory.class).in(Scopes.SINGLETON);
		bind(BatchLauncher.class).in(Scopes.SINGLETON);
	}

	@Provides
	@Named(MyJob.JOB_NAME)
	private JobDetail getJobDetail(){
		
		return newJob(MyJob.class).withIdentity(
				MyJob.JOB_NAME, MyJob.JOB_GROUP)
				.build();
	}
	
	@Inject
	@Provides
	@Named(MyJob.TRIGGER_NAME)
	private Trigger getJobTrigger(Configuration config){
		
		return newTrigger().withIdentity(MyJob.TRIGGER_NAME, MyJob.TRIGGER_GROUP)
				.withSchedule(cronSchedule(config.getString(MyJob.CRON_KEY))).build();
	}
	

}
