package com.fg.ss.abhiyog.common.service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fg.ss.abhiyog.common.model.Role;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.repository.RoleRepository;
import com.fg.ss.abhiyog.common.repository.UserRepository;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.ChangePasswordVO;
import com.fg.ss.abhiyog.common.vo.UserVO;



@Service
@Transactional
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	@Override
	public User findUserByLoginId(String loginId) {
		User user = userRepository.findByLoginId(loginId);
		if(user == null) {
			return null;
		}
		return user;
	}

	@Override
	public BaseResponseVO saveUserData(UserVO userVO) {
		User user = new User();
		
		user.setFirstName(userVO.getFirstName());
		user.setMiddleName(userVO.getMiddleName());
		user.setLastName(userVO.getLastName());
		user.setAddress(userVO.getAddress());
		user.setCity(userVO.getCity());
		user.setGender(userVO.getGender());
		user.setEmailId(userVO.getEmailId());
		user.setPersonalEmailId(userVO.getPersonalEmailId());
		user.setPhone(userVO.getPhone());
		user.setMobile(userVO.getMobile());

		user.setLoginId(userVO.getLoginId());
		user.setPassword(bCryptPasswordEncoder.encode(userVO.getPassword()));
		user.setStatus(userVO.getStatus());
		LocalDateTime dateTime =  LocalDateTime.now();
		user.setCreatedate(dateTime);
		
		Role roles = findByRole(userVO.getRole());
		System.out.println("roles:"+roles.getId());
		if(roles != null) {
			user.setRoles(roles);
			userRepository.save(user);
			baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
			baseResponseVO.setResponseMessage("USER CREATED SUCCESSFULLY");
		}else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("INVALID ROLE");
		}
		return baseResponseVO;
	}

	@Override
	public List<UserVO> getAllUsers() {
		List<User> allUsers = userRepository.getAllUsers();
		if(allUsers == null) {
			System.out.println("AllUsers:: " +allUsers.size());
			return null;
		}
		System.out.println("AllUsers:: " +allUsers.size());
		Type listType = new TypeToken<List<UserVO>>(){}.getType();
        List<UserVO> userVOList = modelMapper.map(allUsers, listType);
        return userVOList;
//		return allUsers.stream().map(users->convertToDto(users)).collect(Collectors.toList());
	}

	@Override
	public UserVO findUserDtlsByLoginId(String loginId) {
		User userDtls = userRepository.findUserDtlsByLoginId(loginId);
		if(userDtls == null) {
			return null;
		}
		UserVO userDto = convertToDto(userDtls);
		return userDto;
	}

	@Override
	public Role findByRole(String role) {
		Role roles = roleRepository.findByRoleDesc(role);
		return roles;
	}

	@Override
	public int update(String firstName, String lastName, String middleName, String phone, String mobile,
			String emailId, String personalEmailId, String address, String city, int roleId, String loginId) {
		int updated = userRepository.updateUser(firstName, lastName,middleName,phone,mobile,
				emailId, personalEmailId, address, city, roleId, loginId);
		
		return updated;
		
	}

	@Override
	public BaseResponseVO changePassword(ChangePasswordVO changePasswordVO) {
//		BaseResponseVO baseResponseVO = new BaseResponseVO();
		User user = new User();
		
		user = userRepository.getPassword(changePasswordVO.getLoginId());
		
			if(bCryptPasswordEncoder.matches(changePasswordVO.getOldPassword(), user.getPassword())) {
				if(changePasswordVO.getNewPassword().equals(changePasswordVO.getConfirmNewPassword())){
					int	passwordChanged = userRepository.changePassword(changePasswordVO.getLoginId(),bCryptPasswordEncoder.encode(changePasswordVO.getNewPassword()));
					if(passwordChanged > 0) {
						baseResponseVO.setResponseCode(HttpStatus.OK.value());
						baseResponseVO.setResponseMessage("PASSWORD CHANGED SUCCESSFULLY");
					}else {
						baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
						baseResponseVO.setResponseMessage("UNABLE TO CHANGE PASSWORD");
					}
				}else {
					baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
					baseResponseVO.setResponseMessage("NEW and CONFIRM NEW PASSWORD SHOULD BE EQUAL");
				}
			}else {
				baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
				baseResponseVO.setResponseMessage("INVALID OLD PASSWORD");
			}
			baseResponseVO.setData(null);
		
		return baseResponseVO;
	}

	private UserVO convertToDto(User users) {
		UserVO userDto = new UserVO();
		userDto.setLoginId(users.getLoginId());
		userDto.setFirstName(users.getFirstName()+ " " +users.getLastName());
		userDto.setEmailId(users.getEmailId());
		userDto.setMobile(users.getMobile());
		userDto.setRoleDesc(users.getRoles().getRoleDesc());
		return userDto;
	}

	
	

	/*public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findUserByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRole(userRole);
		return userRepository.save(user);
	}*/
}
