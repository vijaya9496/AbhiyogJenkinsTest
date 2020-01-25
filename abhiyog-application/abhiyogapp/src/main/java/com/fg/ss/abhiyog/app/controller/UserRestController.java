package com.fg.ss.abhiyog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fg.ss.abhiyog.common.dto.UpdateDTO;
import com.fg.ss.abhiyog.common.model.Role;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.service.EmailService;
import com.fg.ss.abhiyog.common.service.IUserService;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.ChangePasswordVO;
import com.fg.ss.abhiyog.common.vo.ResetPasswordVO;
import com.fg.ss.abhiyog.common.vo.UserVO;

@RestController
@RequestMapping("/masters/user")
public class UserRestController {

	@Autowired
	private IUserService userService;

	@Autowired
	private EmailService emailService;

	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@GetMapping("/getMsg")
	public String sayHi() {

		return "Hello";
	}

	@PostMapping("/addNewUser")
	public ResponseEntity<BaseResponseVO> save(@RequestBody UserVO userVO) {

		User user = userService.findUserByLoginId(userVO.getLoginId());
		if (user == null) {
			baseResponseVO = userService.saveUserData(userVO);
		} else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("USER ALREADY EXISTED");

		}
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);

	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<BaseResponseVO> getUsers() {
		List<UserVO> allUsers = userService.getAllUsers();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allUsers);
		return ResponseEntity.ok().body(baseResponseVO);

	}

	@GetMapping("/getUserById/{loginId}")
	public ResponseEntity<BaseResponseVO> getUsersById(@PathVariable String loginId) {
		UserVO userDtls = userService.findUserDtlsByLoginId(loginId);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(userDtls);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<BaseResponseVO> updateUserById(@RequestBody UpdateDTO updateDto) {
		User user = userService.findUserByLoginId(updateDto.getLoginId());
		if (user != null) {
			Role roles = userService.findByRole(updateDto.getRole());

			if (roles != null) {
				int isUpdated = userService.update(updateDto.getFirstName(), updateDto.getLastName(),
						updateDto.getMiddleName(), updateDto.getPhone(), updateDto.getMobile(), updateDto.getEmailId(),
						updateDto.getPersonalEmailId(), updateDto.getAddress(), updateDto.getCity(), roles.getId(),
						updateDto.getLoginId());
				if (isUpdated > 0) {
					baseResponseVO.setResponseCode(HttpStatus.OK.value());
					baseResponseVO.setResponseMessage("USER UPDATED SUCCESSFULLY");
				}
			} else {
				baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
				baseResponseVO.setResponseMessage("INVALID ROLE");
			}
		} else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("USER NOT EXISTED TO UPDATE");
		}
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);

	}

	@PostMapping("/changePassword")
	public ResponseEntity<BaseResponseVO> changePassword(@RequestBody ChangePasswordVO changePasswordVO) {
		baseResponseVO = userService.changePassword(changePasswordVO);
		return ResponseEntity.ok().body(baseResponseVO);

	}

	@PostMapping("/resetPassword")
	public ResponseEntity<BaseResponseVO> resetPassword(@RequestBody ResetPasswordVO resetPasswordVO) {
		User user = userService.findUserByLoginId(resetPasswordVO.getLoginId());
		baseResponseVO = emailService.sendMail(user.getEmailId(), resetPasswordVO.getLoginId());
		return ResponseEntity.ok().body(baseResponseVO);

	}
}
