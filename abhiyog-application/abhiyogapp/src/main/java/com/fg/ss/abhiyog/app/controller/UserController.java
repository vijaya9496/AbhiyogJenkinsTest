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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fg.ss.abhiyog.common.constants.CommonConstants;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.service.IUserService;
import com.fg.ss.abhiyog.common.vo.EntityVO;
import com.fg.ss.abhiyog.common.vo.UserVO;

@Controller
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/showUserSummary", method = RequestMethod.GET)
	public String showUserSummary(Model model) {
		model.addAttribute("userVO", new UserVO());
		model.addAttribute("allRoles", userService.getAllRoles());
		return "userSummary";
	}

	@RequestMapping(value = "/getUserSummary", method = RequestMethod.GET)
	public void getUserSummary(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<UserVO> allUsers = userService.getAllUsers();
		fillGridDetail(allUsers, request, response, session);
	}

	private void fillGridDetail(List<UserVO> allUsers, HttpServletRequest request, HttpServletResponse response,
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
			if (allUsers.size() > 0) {
				totalRecord = allUsers.size();
				for (UserVO userSummary : allUsers) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, userSummary.getId());
					cell = mapper.createArrayNode();
					cell.add(userSummary.getId());
					cell.add(userSummary.getLoginId());
					cell.add(userSummary.getFirstName());
					cell.add(userSummary.getRoleDesc());
					cell.add(userSummary.getEmailId());
					cell.add(userSummary.getMobile());
					cell.add(userSummary.getLawfirm());
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

	@RequestMapping("/getUserSummaryBy")
	public void userSummaryData(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		LOGGER.info("inside litigationSummaryData");
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			String role = "";
			String status = "";
			System.out.println(request.getParameter("role"));
			System.out.println(request.getParameter("status"));
			
			if (request.getParameter("role") != "ALL" && request.getParameter("role") != null
					&& request.getParameter("role") != "") {
				role = request.getParameter("role");
			}
			if (request.getParameter("status") != "ALL" && request.getParameter("status") != null
					&& request.getParameter("status") != "") {
				status = request.getParameter("status");
			}

			List<UserVO> userSummary = userService.getUserSummaryData(role, status);
			if (userSummary.size() > 0) {
				totalRecord = userSummary.size();
				for (UserVO userDtls : userSummary) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, userDtls.getId());
					cell = mapper.createArrayNode();
					cell.add(userDtls.getId());
					cell.add(userDtls.getLoginId());
					cell.add(userDtls.getFirstName());
					cell.add(userDtls.getRoleDesc());
					cell.add(userDtls.getEmailId());
					cell.add(userDtls.getMobile());
					cell.add(userDtls.getLawfirm());
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
			LOGGER.error("Exception generated in getUserSummaryBy Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value="/viewUser")
	public String viewUserProfile(Model model,HttpServletRequest request) {
		model.addAttribute("userVO", new UserVO());
		LOGGER.info("ID::" +request.getParameter("id"));
		model.addAttribute("allUserDtls", userService.getUserProfile(Integer.parseInt(request.getParameter("id"))));
		return "viewUserProfile";
		
	}
	
	@RequestMapping(value="/newUser", method=RequestMethod.GET)
	public String newUser(Model model) {
		model.addAttribute("userVO", new UserVO());
		model.addAttribute("allRoles", userService.getAllRoles());
		return "newUser";
	}

	@RequestMapping(value="/addNewUser", method=RequestMethod.POST)
	public  String addNewUser(@ModelAttribute UserVO user, Model model) {
		LOGGER.info("inside addNewUser method");
		
		User checkUserExistence = userService.findUserByLoginId(user.getLoginId());
		LOGGER.info("LoginId"+user.getLoginId());
		if(checkUserExistence != null) {
			model.addAttribute("userVO",new UserVO());
			model.addAttribute("message", "Login Id Already Existed");
			model.addAttribute("allRoles", userService.getAllRoles());
			
		}else {
			LOGGER.info("Role::" +user.getRoleDesc());
			LOGGER.info("FirstName" +user.getFirstName());
 			boolean isInserted = userService.saveUserData(user);
			if(isInserted) {
				model.addAttribute("message", "New User Added Successfully");
				model.addAttribute("userVO", new UserVO());
				model.addAttribute("allRoles", userService.getAllRoles());
			}else {
				model.addAttribute("userVO", user);
				model.addAttribute("message", "Unable to Add New User");
				model.addAttribute("allRoles", userService.getAllRoles());
			}
		}
		return "newUser";
	}
	
	@RequestMapping(value="/updateUser", method=RequestMethod.GET)
	public String updateUser(Model model, HttpServletRequest request) {
		model.addAttribute("allRoles", userService.getAllRoles());
		model.addAttribute("userDtls", userService.getUserProfile(Integer.parseInt(request.getParameter("id"))));
		return "updateUser";
	}
	
	@RequestMapping(value="/updateUserDtls", method=RequestMethod.POST)
	public String updateUserDtls(@ModelAttribute UserVO userVO, Model model) {
		LOGGER.info("Inside updateUserDtls");
		boolean isUpdated = userService.saveUserData(userVO);
		if (isUpdated) {
			model.addAttribute("message","USER UPDATED SUCCESSFULLY");
			model.addAttribute("userDtls", new UserVO());
			model.addAttribute("allRoles", userService.getAllRoles());
		}else {
			model.addAttribute("message","UNABLE TO UPDATED USER DETAILS");
			model.addAttribute("userDtls", new UserVO());
			model.addAttribute("allRoles", userService.getAllRoles());
		}
		return "updateUser";
	}
}
