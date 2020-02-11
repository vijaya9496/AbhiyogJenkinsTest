package com.fg.ss.abhiyog.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.repository.UserRepository;
import com.fg.ss.abhiyog.common.vo.UserVO;

@Service
public class LoginService implements ILoginService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
	@Override
	public UserVO validateUser(String loginId, String password) {
		//Check loginId Existence with active status
		User user = userRepository.findByLoginId(loginId);
		
		if(user == null) {
			return null;
		}else {
			if(bcryptPasswordEncoder.matches(password, user.getPassword())) {
				UserVO userVO = new UserVO();
				userVO.setLoginId(user.getLoginId());
				userVO.setFirstName(user.getFirstName());
				userVO.setMiddleName(user.getMiddleName());
				userVO.setLastName(user.getLastName());
				userVO.setPassword(user.getPassword());
				userVO.setEmailId(user.getEmailId());
				userVO.setPersonalEmailId(user.getPersonalEmailId());
				userVO.setAddress(user.getAddress());
				userVO.setCity(user.getCity());
				userVO.setStatus(user.getStatus());
				userVO.setRoleId(user.getRoles().getId());
				return userVO;
			}else {
				return null;
			}
		}
	}

}
