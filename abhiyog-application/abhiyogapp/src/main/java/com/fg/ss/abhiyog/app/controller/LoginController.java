package com.fg.ss.abhiyog.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fg.ss.abhiyog.common.constants.CommonConstants;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.service.EmailService;
import com.fg.ss.abhiyog.common.service.ILoginService;
import com.fg.ss.abhiyog.common.service.IUserService;
import com.fg.ss.abhiyog.common.vo.UserVO;



@Controller
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	private static final String USER_LOGIN = "userLogin";
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value="*", method = RequestMethod.GET)
	public String welComeLogin(Model model) {
		model.addAttribute("userVO", new UserVO());
		return "userLogin";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		System.out.println("inside Logout action start!!");
		LOGGER.info("inside Logout action start!!");
		model.addAttribute("userVO", new UserVO());
		HttpSession session = request.getSession(true);
		session.setAttribute(CommonConstants.SESSION_USER_VO, null);
		System.out.println("inside Logout action End!!");
		LOGGER.info("inside Logout action End!!");
		return "userLogin";
	}
	
	@RequestMapping(value="/validateUser", method=RequestMethod.POST)
	public ModelAndView validateUser(@ModelAttribute UserVO userVO, HttpServletRequest request) {
		System.out.println("LoginId: " +userVO.getLoginId());
		LOGGER.info("LoginId: " +userVO.getLoginId());
		ModelAndView modelAndView = new ModelAndView();
		UserVO user = loginService.validateUser(userVO.getLoginId(), userVO.getPassword());
		if(user == null) {
			modelAndView.setViewName(USER_LOGIN);
			modelAndView.addObject("message", "Invalid Credential or InActive User");
		}else if(user != null && !bCryptPasswordEncoder.matches(userVO.getPassword(), user.getPassword())) {
			modelAndView.setViewName(USER_LOGIN);
			modelAndView.addObject("message", "Invalid Credential or InActive User");
		}else {
			user.setFullName(user.getFirstName()+ " "+user.getMiddleName()+ " " +user.getLastName());
			HttpSession session = request.getSession(true);
			session.setAttribute(CommonConstants.SESSION_USER_VO, user);
			modelAndView.setViewName("dashboard");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/forgetPassword", method=RequestMethod.GET)
	public String forgetPassword(Model model) {
		model.addAttribute("userVO", new UserVO());
		return "forgetPassword";
	}
	
	
	@RequestMapping(value="/resetPassword", method=RequestMethod.POST)
	public ModelAndView resetPassword(@ModelAttribute UserVO userVO, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("LoginID:: " +userVO.getLoginId());
		LOGGER.info("LoginID:: " +userVO.getLoginId());
		User user = userService.findUserByLoginId(userVO.getLoginId());
		boolean mailSent = emailService.sendMail(user.getEmailId(), user.getLoginId());
		if(mailSent) {
			modelAndView.addObject("message", "Password has been sent to your mail");
			modelAndView.addObject("userVO", userVO);
			modelAndView.setViewName("forgetPassword");
		}else {
			modelAndView.addObject("message", "Invalid Credential or InActive User");
			modelAndView.addObject("userVO", userVO);
			modelAndView.setViewName("forgetPassword");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/changePassword", method=RequestMethod.GET)
	public String changePassword(Model model) {
		model.addAttribute("userVO", new UserVO());
		return "changePassword";
	}
	
	@RequestMapping(value="/updatePassword", method=RequestMethod.POST)
	public ModelAndView updatePassword(@ModelAttribute UserVO userVO, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		UserVO uservo=  (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
		LOGGER.info("OldPswd:: " +userVO.getOldPassword() );
		LOGGER.info("NewPswd:: " +userVO.getNewPassword() );
		LOGGER.info("LoginID:: " +userVO.getLoginId() );
		userVO.setLoginId(uservo.getLoginId());
		int passwordChanged = userService.changePassword(userVO);
		if(passwordChanged > 0) {
			modelAndView.addObject("message", "Password has been Changed Successfully");
			modelAndView.addObject("userVO", userVO);
			modelAndView.setViewName("changePassword");
		}else {
			modelAndView.addObject("message", "Invalid Credential or InActive User");
			modelAndView.addObject("userVO", userVO);
			modelAndView.setViewName("changePassword");
		}
		return modelAndView;

	}
}
