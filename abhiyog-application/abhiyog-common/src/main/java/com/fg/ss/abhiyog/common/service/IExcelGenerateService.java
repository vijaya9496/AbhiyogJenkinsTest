package com.fg.ss.abhiyog.common.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.fg.ss.abhiyog.common.model.Litigation;

public interface IExcelGenerateService {

	Workbook generateHearingStatusExcelReport(List<Litigation> hearingStatusDtls);

}
