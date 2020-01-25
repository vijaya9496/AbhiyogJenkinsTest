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
	@Query(value = "update User u set u.firstName=:firstName, u.lastName=:lastName, u.middleName=:middleName, u.phone=:phone, u.mobile=:mobile, "
			+ "u.emailId=:emailId, u.personalEmailId=:personalEmailId, u.address=:address, u.city=:city, u.roles.id=:roleId where u.loginId=:loginId")
	int updateUser(@Param("firstName")String firstName, @Param("lastName")String lastName, @Param("middleName")String middleName, @Param("phone")String phone, @Param("mobile")String mobile, @Param("emailId")String emailId,
			@Param("personalEmailId")String personalEmailId, @Param("address")String address, @Param("city")String city, @Param("roleId")int roleId, @Param("loginId")String loginId);

	@Query(value = "select u from User u where u.loginId=:loginId")
	User getPassword(@Param("loginId")String loginId);

	@Modifying
	@Transactional
	@Query(value = "update User u set u.password=:encodeNewPassword where u.loginId=:loginId")
	int changePassword(@Param("loginId")String loginId, @Param("encodeNewPassword")String encodeNewPassword);

	@Query(value="select u from User as u where u.roles.id = 6")
	List<User> findToEmailIds();

}
