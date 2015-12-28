package com.app.batch;

import javax.inject.Provider;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class CoreModule extends AbstractModule {
	protected void configure() {
		bind(Configuration.class).toProvider(ConfigurationProvider.class).in(
				Scopes.SINGLETON);
	}

	private static class ConfigurationProvider implements
			Provider<Configuration> {
		public Configuration get() {
			try {
				Configuration result = new PropertiesConfiguration(
						"config.properties");
				return result;
			} catch (ConfigurationException e) {
				logger.warn("can not load configuration config.properties.",e);
				return null;
			}
		}
	}
	
	private static final Logger logger = LoggerFactory.getLogger(CoreModule.class);
}