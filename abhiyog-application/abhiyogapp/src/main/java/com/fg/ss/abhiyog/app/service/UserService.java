package com.fg.ss.abhiyog.app.service;
/*package com.fg.ss.abhiyogapp.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyogapp.model.Role;
import com.fg.ss.abhiyogapp.model.User;
import com.fg.ss.abhiyogapp.repository.RoleRepository;
import com.fg.ss.abhiyogapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	

	public User findUserByEmail(String email) {
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
	}
}*/
