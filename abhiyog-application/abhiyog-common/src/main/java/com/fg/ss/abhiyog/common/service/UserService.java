package com.fg.ss.abhiyog.common.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.Role;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.repository.RoleRepository;
import com.fg.ss.abhiyog.common.repository.UserRepository;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.UserVO;



@Service
@Transactional
public class UserService implements IUserService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EntityManager entityManager;
	
	
	
	@Override
	public User findUserByLoginId(String loginId) {
		User user = userRepository.findByLoginId(loginId);
		if(user == null) {
			return null;
		}
		return user;
	}

	@Override
	public boolean saveUserData(UserVO userVO) {
		boolean isInserted = false;
		User user = new User();
		if(userVO.getLoginId() != null) {
			User checkUserExistence = userRepository.findByLoginId(userVO.getLoginId());
			if(checkUserExistence != null) {
				user.setId(checkUserExistence.getId());
				user.setPassword(checkUserExistence.getPassword());
				user.setStatus(userVO.getStatus());
			}else {
				user.setStatus("active");
				user.setPassword(bCryptPasswordEncoder.encode(userVO.getPassword()));
			}
		}
		user.setFirstName(userVO.getFirstName());
		user.setMiddleName(userVO.getMiddleName());
		user.setLastName(userVO.getLastName());
		user.setAddress(userVO.getAddress());
		user.setCity(userVO.getCity());
		user.setGender("Male");
		user.setEmailId(userVO.getEmailId());
		user.setPersonalEmailId(userVO.getPersonalEmailId());
		user.setPhone(userVO.getPhone());
		user.setMobile(userVO.getMobile());
		user.setLoginId(userVO.getLoginId());
		LocalDateTime dateTime =  LocalDateTime.now();
		user.setCreatedate(dateTime);
		Role roles = findByRole(userVO.getRoleDesc());
		if(roles != null) {
			user.setRoles(roles);
			userRepository.save(user);
			isInserted = true;
		}
		return isInserted;
	}

	@Override
	public List<UserVO> getAllUsers() {
		List<User> allUsers = userRepository.getAllUsers();
		if(allUsers == null) {
			System.out.println("AllUsers:: " +allUsers.size());
			return null;
		}
		/*System.out.println("AllUsers:: " +allUsers.size());
		Type listType = new TypeToken<List<UserVO>>(){}.getType();
        List<UserVO> userVOList = modelMapper.map(allUsers, listType);
        return userVOList;*/
		return allUsers.stream().map(users->convertToDto(users)).collect(Collectors.toList());
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

/*	@Override
	public int update(UserVO user) {
		int updated = userRepository.updateUser(user);
		
		return updated;
		
	}*/

	@Override
	public int changePassword(UserVO userVO) {
//		BaseResponseVO baseResponseVO = new BaseResponseVO();
		User user = new User();
		
		user = userRepository.findByLoginId(userVO.getLoginId());
			int	passwordChanged = 0;
			if(bCryptPasswordEncoder.matches(userVO.getOldPassword(), user.getPassword())) {
				if(userVO.getNewPassword().equals(userVO.getConfirmNewPassword())){
					passwordChanged = userRepository.changePassword(userVO.getLoginId(),bCryptPasswordEncoder.encode(userVO.getNewPassword()));
					return passwordChanged;
				}
			}
		return passwordChanged;
	}

	private UserVO convertToDto(User users) {
		UserVO userDto = new UserVO();
		userDto.setId(users.getId());
		userDto.setLoginId(users.getLoginId());
		userDto.setFirstName(users.getFirstName()+ " " +users.getLastName());
		userDto.setEmailId(users.getEmailId());
		userDto.setMobile(users.getMobile());
		userDto.setRoleDesc(users.getRoles().getRoleDesc());
		return userDto;
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> allRoles = roleRepository.findAll();
		return allRoles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVO> getUserSummaryData(String role, String status) {
		List<User> userSummary = new ArrayList<>();
		Map<String, Object> parameterMap = new HashMap<>();
		List<String> whereClause = new ArrayList<>();
		StringBuilder reportQuery = new StringBuilder();
		
		reportQuery.append("select u from User u join Role r on r.id=u.roles.id ");
		
		if(!role.equals("ALL")) {
			System.out.println(role);
			whereClause.add(" r.roleDesc=:role ");
			parameterMap.put("role", role);
//			reportQuery.append( StringUtils.join(whereClause, " and "));
		} 
		if(!status.equals("ALL")) {
			whereClause.add(" u.status=:status ");
			parameterMap.put("status", status);
//			reportQuery.append(" where " + StringUtils.join(whereClause, " and "));
		} 
		if(!status.equals("ALL") || !role.equals("ALL")) {
			reportQuery.append(" where " + StringUtils.join(whereClause, " and "));
		}
		
		Query jpaQuery = entityManager.createQuery(reportQuery.toString());
		LOGGER.info("Created Query::  " + reportQuery.toString());
		for(String key: parameterMap.keySet()) {
			jpaQuery.setParameter(key, parameterMap.get(key));
		}
		userSummary = jpaQuery.getResultList();
		LOGGER.info("userSummary Size:: " +userSummary.size());
		return userSummary.stream().map(allUserSummaryDtls ->convertToDto(allUserSummaryDtls)).collect(Collectors.toList());
	}

	@Override
	public UserVO getUserProfile(int id) {
		User userProfileDtls = userRepository.getUserProfileByID(id);
		UserVO userVO = new UserVO();
		userVO.setLoginId(userProfileDtls.getLoginId());
		userVO.setRoleDesc(userProfileDtls.getRoles().getRoleDesc());
		userVO.setStatus(userProfileDtls.getStatus());
		userVO.setFirstName(userProfileDtls.getFirstName());
		userVO.setMiddleName(userProfileDtls.getMiddleName());
		userVO.setLastName(userProfileDtls.getLastName());
		userVO.setAddress(userProfileDtls.getAddress());
		userVO.setCity(userProfileDtls.getCity());
		userVO.setPhone(userProfileDtls.getPhone());
		userVO.setMobile(userProfileDtls.getMobile());
		userVO.setEmailId(userProfileDtls.getEmailId());
		userVO.setPersonalEmailId(userProfileDtls.getPersonalEmailId());
		return userVO;
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
