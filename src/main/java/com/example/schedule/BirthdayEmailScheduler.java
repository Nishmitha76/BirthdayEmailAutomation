package com.example.schedule;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.service.BirthdayEmailServiceImpl;

@Component
public class BirthdayEmailScheduler {
	
	@Autowired
	private BirthdayEmailServiceImpl service;
	
	@Scheduled(cron = "0 45 14 * * ?")//Runs every day at 2:45pm everyday
	public void scheduleBirthdayEmails() {
		System.out.println("Birthday email job triggered at " + LocalDateTime.now());
		service.sendBirthdayEmails();
		System.out.println("cron job completed!!!!");
	}

}
