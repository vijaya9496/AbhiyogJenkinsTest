package com.fg.ss.abhiyog.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.service.EmailService;
import com.fg.ss.abhiyog.common.service.IEmailAlertSchedularService;

@Component
public class EmailAlertSchedular {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailAlertSchedular.class);
	
	@Autowired
	private IEmailAlertSchedularService emailAlertSchedularService;
	
	@Autowired
	private EmailService emailService;
	
	@Scheduled(cron="${cronExpression}", zone="IST")
	public void emailSendSchedular() {
		LOGGER.info("emailSendSchedular initiated");
		//to get next seven days hearingdate details
		LOGGER.info("Fetching HearingDateDetails");
		List<Litigation> hearingDateDtls = emailAlertSchedularService.findNextSevenDaysHearingDateDtls();
		LOGGER.info("hearingDateDtls size:: " +hearingDateDtls.size());
		//to get all toemailId list based on roleId
		LOGGER.info("Fetching getToEmailIds");
		List<User> toEmailIdList = emailAlertSchedularService.getToEmailIds();
		LOGGER.info("toEmailIdList Size:: " +toEmailIdList.size());
		//Mail send to every toEmailId
		LOGGER.info("Sending Email Alert");
		emailService.sendEmailAlert(hearingDateDtls, toEmailIdList);
		
	}
}
