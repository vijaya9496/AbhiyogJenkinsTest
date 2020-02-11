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
import org.springframework.http.HttpStatus;
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
import com.fg.ss.abhiyog.common.service.IEntityService;
import com.fg.ss.abhiyog.common.vo.EntityVO;
import com.fg.ss.abhiyog.common.vo.UserVO;

@Controller
public class EntityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IEntityService entityService;
	
	@RequestMapping(value = "/showEntitySummary", method = RequestMethod.GET)
	public String showUserSummary(Model model) {
		model.addAttribute("entityVO", new EntityVO());
		return "entitySummary";
	}
	
	@RequestMapping(value="/getEntitySummary", method=RequestMethod.GET)
	public void getEntitySummary(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<EntityVO> allEntities = entityService.getAllEntities();
		fillGridDetail(allEntities, request, response, session);
	}

	private void fillGridDetail(List<EntityVO> allEntities, HttpServletRequest request, HttpServletResponse response,
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
			if (allEntities.size() > 0) {
				totalRecord = allEntities.size();
				for (EntityVO entitySummary : allEntities) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, entitySummary.getEntityId());
					cell = mapper.createArrayNode();
					cell.add(entitySummary.getEntityId());
					cell.add(entitySummary.getEntityName());
					cell.add("Not Defined");
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
	
	@RequestMapping(value="/newEntity", method=RequestMethod.GET)
	public String newUser(Model model) {
		model.addAttribute("entityVO", new EntityVO());
		return "addNewEntity";
	}
	
	@RequestMapping(value="/addNewEntity", method=RequestMethod.POST)
	public String addNewUser(@ModelAttribute EntityVO entityVO, Model model) {
		EntitySummary entity = entityService.getEntityByName(entityVO.getEntityName());
		if(entity != null) {
			model.addAttribute("entityVO",new EntityVO());
			model.addAttribute("message", "ENTITY NAME ALREADY EXISTED");
		}else {
			entityService.saveEntityData(entityVO.getEntityName());
			model.addAttribute("entityVO",new EntityVO());
			model.addAttribute("message", "ENTITY NAME ADDED SUCCESSFULLY");
		}
		return "addNewEntity";
		
	}
	
	@RequestMapping(value="/updateEntity", method=RequestMethod.GET)
	public String updateEntity(Model model, HttpServletRequest request) {
		model.addAttribute("entityVO", new EntityVO());
		model.addAttribute("entityName", entityService.findById(Integer.parseInt(request.getParameter("id"))));
		return "updateEntity";
	}
	
	//update EntityName by id
	@RequestMapping(value="/updateEntityDtls", method=RequestMethod.POST)
	public String updateEntityDtls(@ModelAttribute EntityVO entityVO, Model model) {
		System.out.println("Inside updateEntityDtls");
		System.out.println("EntityName:: " +entityVO.getEntityName());
		System.out.println("UpdatedEntityName:: " +entityVO.getUpdatedEntityName());
		EntitySummary entity = entityService.getEntityByName(entityVO.getEntityName());
		if(entity != null) {
			int isUpdated = entityService.updateEntityByName(entityVO.getEntityName(), entityVO.getUpdatedEntityName());
			if(isUpdated > 0) {
				model.addAttribute("message","ENTITY NAME UPDATED SUCCESSFULLY");
			}else {
				model.addAttribute("message","GIVEN ENTITY NAME ALREADY EXISTED");
			}
		}else {
			model.addAttribute("message","ENTITY NAME NOT EXISTED");
		}
		
		return "updateEntity";
		
	}
}
