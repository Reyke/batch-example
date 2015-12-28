package com.app.batch.service;

import java.util.Calendar;

public class MyService {
	
	public void printCurrentTime(){
		System.out.println("current time: " + Calendar.getInstance().getTime().toString());
	}

}
