package com.fg.ss.abhiyog.common.service;

import java.util.List;

import com.fg.ss.abhiyog.common.model.Role;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.ChangePasswordVO;
import com.fg.ss.abhiyog.common.vo.UserVO;

public interface IUserService {

	User findUserByLoginId(String loginId);

	BaseResponseVO saveUserData(UserVO userVO);

	List<UserVO> getAllUsers();

	UserVO findUserDtlsByLoginId(String loginId);

	Role findByRole(String role);

	int update(String firstName, String lastName, String middleName, String phone, String mobile, String emailId,
			String personalEmailId, String address, String city, int roleId, String loginId);

	BaseResponseVO changePassword(ChangePasswordVO changePasswordVO);

}
