package com.fg.ss.abhiyog.common.service;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.fg.ss.abhiyog.common.model.Litigation;

public interface IWordGenerateService {

	XWPFDocument generateHearingStatusWordReport(List<Litigation> hearingStatusDtls, String fileName);

}
