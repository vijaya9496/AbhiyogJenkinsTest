package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fg.ss.abhiyog.common.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "select u from User u where u.loginId=:loginId")
	User findByLoginId(@Param("loginId")String loginId);

	@Query(value = "select u from User u,Role r where r.id=u.roles.id")
	List<User> getAllUsers();

	@Query(value = "select u from User u, Role r where r.id = u.roles.id and u.loginId=:loginId")
	User findUserDtlsByLoginId(@Param("loginId")String loginId);

	@Modifying
	@Transactional
	@Query(value = "update User u set u.firstName=?1, u.lastName=?2, u.middleName=?3, u.phone=?4, u.mobile=?5, "
			+ "u.emailId=?6, u.personalEmailId=?7, u.address=?8, u.city=?9, u.roles.id=?10 where u.loginId=?11")
	int updateUser(String firstName, String lastName, String middleName, String phone, String mobile, String emailId,
			String personalEmailId, String address, String city, int roleId, String loginId);

	@Query(value = "select u from User u where u.loginId=:loginId")
	User getPassword(@Param("loginId")String loginId);

	@Modifying
	@Transactional
	@Query(value = "update User u set u.password=:encodeNewPassword where u.loginId=:loginId")
	int changePassword(@Param("loginId")String loginId, @Param("encodeNewPassword")String encodeNewPassword);

}
