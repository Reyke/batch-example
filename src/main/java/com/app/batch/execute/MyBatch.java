package com.app.batch.execute;

import com.app.batch.BatchLauncher;
import com.app.batch.BatchModule;
import com.app.batch.CoreModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class MyBatch {

	public static void main(String[] args) {
		
		Injector injector = Guice.createInjector(new CoreModule(),new BatchModule());
		
		BatchLauncher launcher = injector.getInstance(BatchLauncher.class);
		launcher.executeJobs();

	}

}
