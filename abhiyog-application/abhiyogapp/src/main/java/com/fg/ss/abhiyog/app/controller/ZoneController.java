package com.fg.ss.abhiyog.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fg.ss.abhiyog.common.constants.CommonConstants;
import com.fg.ss.abhiyog.common.model.Zone;
import com.fg.ss.abhiyog.common.service.IZoneService;
import com.fg.ss.abhiyog.common.vo.ZoneVO;

@Controller
public class ZoneController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZoneController.class);

	@Autowired
	private IZoneService zoneService;

	@RequestMapping(value = "/showRegionSummary", method = RequestMethod.GET)
	public String showFormatSummary(Model model) {
		model.addAttribute("zoneVO", new ZoneVO());
		return "regionsSummary";
	}

	@RequestMapping(value = "/getRegionSummary", method = RequestMethod.GET)
	public void getFormatSummary(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<ZoneVO> allZones = zoneService.getAllZones();
		fillGridDetail(allZones, request, response, session);
	}

	private void fillGridDetail(List<ZoneVO> allZones, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		LOGGER.info("inside fillGridDetail method");
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			if (allZones.size() > 0) {
				totalRecord = allZones.size();
				for (ZoneVO regionSummary : allZones) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, regionSummary.getZoneId());
					cell = mapper.createArrayNode();
					cell.add(regionSummary.getZoneId());
					cell.add(regionSummary.getZoneName());
					cell.add("Update");
//					cellObj.put(CommonConstants.CELL, cell);
					cellObj.set(CommonConstants.CELL, cell);
					cellArray.add(cellObj);
				}
			}
			responseData.put(CommonConstants.PAGE, CommonConstants.PAGE_NO);
			responseData.put(CommonConstants.RECORDS, totalRecord);
//			responseData.put(CommonConstants.ROWS, cellArray);
			responseData.set(CommonConstants.ROWS, cellArray);
			out.println(responseData);

		} catch (IOException e) {
			LOGGER.error("Exception generated in FillingGrid Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/addNewRegion", method = RequestMethod.POST)
	public String addFormat(@ModelAttribute ZoneVO zoneVO, Model model) {
		System.out.println("Format:: " + zoneVO.getZoneName());
		Zone isZoneExisted = zoneService.checkZoneExistence(zoneVO.getZoneName());
		if (isZoneExisted == null) {
			zoneService.saveZoneData(zoneVO);
			model.addAttribute("message", "Zone Added Successfully");
			model.addAttribute("zoneVO", new ZoneVO());
		} else {
			model.addAttribute("message", "Zone Already Existed");
			model.addAttribute("zoneVO", new ZoneVO());
		}
		return "regionsSummary";
	}

}
