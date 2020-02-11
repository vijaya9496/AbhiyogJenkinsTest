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
import com.fg.ss.abhiyog.common.model.EntitySummary;
import com.fg.ss.abhiyog.common.model.Format;
import com.fg.ss.abhiyog.common.service.IFormatService;
import com.fg.ss.abhiyog.common.vo.EntityVO;
import com.fg.ss.abhiyog.common.vo.FormatVO;
import com.fg.ss.abhiyog.common.vo.UserVO;

@Controller
public class FormatController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FormatController.class);
	
	@Autowired
	private IFormatService formatService;
	
	@RequestMapping(value = "/showFormatSummary", method = RequestMethod.GET)
	public String showFormatSummary(Model model) {
		model.addAttribute("formatVO", new FormatVO());
		return "formatSummary";
	}
	@RequestMapping(value="/getFormatSummary", method=RequestMethod.GET)
	public void getFormatSummary(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<FormatVO> allFormats = formatService.getAllFormats();
		fillGridDetail(allFormats, request, response, session);
	}
	private void fillGridDetail(List<FormatVO> allFormats, HttpServletRequest request, HttpServletResponse response,
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
			if (allFormats.size() > 0) {
				totalRecord = allFormats.size();
				for (FormatVO formatSummary : allFormats) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, formatSummary.getFormatId());
					cell = mapper.createArrayNode();
					cell.add(formatSummary.getFormatId());
					cell.add(formatSummary.getFormat());
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
	
	@RequestMapping(value="/addNewFormat", method=RequestMethod.POST)
	public String addFormat(@ModelAttribute FormatVO formatVO, Model model) {
		System.out.println("Format:: " +formatVO.getFormat());
		Format format = formatService.getFormatByName(formatVO.getFormat());
		if(format != null) {
			model.addAttribute("message", "Format Already Existed");
			model.addAttribute("formatVO", new FormatVO());
		}else {
			formatService.saveFormatData(formatVO);
			model.addAttribute("formatVO", new FormatVO());
			model.addAttribute("message", "Format Added Successfully");
		}
		return "formatSummary";
	}
	
	@RequestMapping(value="/updateFormat", method=RequestMethod.GET)
	public String updateFormat(Model model, HttpServletRequest request) {
		model.addAttribute("formatVO", new FormatVO());
		model.addAttribute("formatName", formatService.findById(Integer.parseInt(request.getParameter("id"))));
		return "updateFormat";
	}
	
	//update EntityName by id
		@RequestMapping(value="/updateFormatDtls", method=RequestMethod.POST)
		public String updateEntityDtls(@ModelAttribute FormatVO formatVO, Model model) {
			System.out.println("Inside updateEntityDtls");
			System.out.println("EntityName:: " +formatVO.getFormat());
			System.out.println("UpdatedEntityName:: " +formatVO.getUpdatedFormatName());
			Format format = formatService.getFormatByName(formatVO.getFormat());
			if(format != null) {
				int isUpdated = formatService.updateFormatByName(formatVO.getFormat(), formatVO.getUpdatedFormatName());
				if(isUpdated > 0) {
					model.addAttribute("message","FORMAT UPDATED SUCCESSFULLY");
				}else {
					model.addAttribute("message","GIVEN FORMAT NAME ALREADY EXISTED");
				}
			}else {
				model.addAttribute("message","FORMAT NAME NOT EXISTED");
			}
			
			return "updateFormat";
			
		}
}
