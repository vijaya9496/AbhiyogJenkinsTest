package com.fg.ss.abhiyog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.service.EmailService;
import com.fg.ss.abhiyog.common.service.IEmailAlertSchedularService;

@Component
public class EmailAlertSchedular {

	@Autowired
	private IEmailAlertSchedularService emailAlertSchedularService;
	
	@Autowired
	private EmailService emailService;
	
	@Scheduled(cron="${cronExpression}", zone="IST")
	public void emailSendSchedular() {
		System.out.println("emailSendSchedular initiated");
		//to get next seven days hearingdate details
		System.out.println("Fetching HearingDateDetails");
		List<Litigation> hearingDateDtls = emailAlertSchedularService.findNextSevenDaysHearingDateDtls();
		System.out.println("hearingDateDtls size:: " +hearingDateDtls.size());
		//to get all toemailId list based on roleId
		System.out.println("Fetching getToEmailIds");
		List<User> toEmailIdList = emailAlertSchedularService.getToEmailIds();
		System.out.println("toEmailIdList Size:: " +toEmailIdList.size());
		//Mail send to every toEmailId
		System.out.println("Sending Email Alert");
		emailService.sendEmailAlert(hearingDateDtls, toEmailIdList);
		
	}
}
