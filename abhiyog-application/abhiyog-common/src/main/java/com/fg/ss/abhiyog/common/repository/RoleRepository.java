package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query(value="select r from Role r where r.roleDesc=:role")
	Role findByRoleDesc(@Param("role")String role);
	
//	  Role findByRole(String role);

}
