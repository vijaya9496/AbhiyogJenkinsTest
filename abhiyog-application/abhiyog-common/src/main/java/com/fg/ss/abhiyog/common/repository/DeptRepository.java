package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.Dept;



@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer>{

	@Query(value="select d from Dept d where d.deptName = :function")
	Dept findByDeptName(@Param("function")String function);

}
