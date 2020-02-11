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
	@Query(value = "select u from User u where u.loginId=:loginId and u.status='active'")
	User findByLoginId(@Param("loginId")String loginId);

	@Query(value = "select u from User u,Role r where r.id=u.roles.id")
	List<User> getAllUsers();

	@Query(value = "select u from User u, Role r where r.id = u.roles.id and u.loginId=:loginId")
	User findUserDtlsByLoginId(@Param("loginId")String loginId);

/*	@Modifying
	@Transactional
	@Query(value = "update User u set u.firstName=:userVO.firstName, u.lastName=:userVO.lastName, u.middleName=:userVO.middleName, u.phone=:userVO.phone, u.mobile=:userVO.mobile, "
			+ "u.emailId=:userVO.emailId, u.personalEmailId=:userVO.personalEmailId, u.address=:userVO.address, u.city=:userVO.city, u.loginId=:userVO.loginId, u.")
	int updateUser(@Param("userVO") UserVO userVO);*/

/*	@Query(value = "select u from User u where u.loginId=:loginId")
	User getPassword(@Param("loginId")String loginId);*/

	@Modifying
	@Transactional
	@Query(value = "update User u set u.password=:encodeNewPassword where u.loginId=:loginId")
	int changePassword(@Param("loginId")String loginId, @Param("encodeNewPassword")String encodeNewPassword);

	@Query(value="select u from User as u where u.roles.id = 6")
	List<User> findToEmailIds();

	@Query(value="select u from User u where u.loginId=:loginId and u.password=:password and u.status='active'")
	User validateUser(@Param("loginId")String loginId, @Param("password")String password);

	@Query(value="select u from User u where u.id=:id")
	User getUserProfileByID(@Param("id")int id);

	@Query(value="select u from User u where u.roles.id in (6,8)")
	List<User> getUnitHeadNames();

	@Query(value="select u from User u where u.firstName=:firstName and u.lastName=:lastName and u.status='active'")
	User findUserBy(@Param("firstName")String firstName, @Param("lastName")String lastName);

}
