package com.fg.ss.abhiyog.common.service;

import java.util.List;
import java.util.Optional;

import com.fg.ss.abhiyog.common.model.Role;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.ChangePasswordVO;
import com.fg.ss.abhiyog.common.vo.UserVO;

public interface IUserService {

	User findUserByLoginId(String loginId);

	boolean saveUserData(UserVO userVO);

	List<UserVO> getAllUsers();

	UserVO findUserDtlsByLoginId(String loginId);

	Role findByRole(String role);

//	int update(UserVO user);

	int changePassword(UserVO userVO);

	List<Role> getAllRoles();

	List<UserVO> getUserSummaryData(String role, String status);

	UserVO getUserProfile(int id);

//	Optional<User> findById(long id);

}
