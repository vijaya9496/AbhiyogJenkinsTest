package com.fg.ss.abhiyog.common.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.Format;



@Repository
public interface FormatRepository extends JpaRepository<Format, Integer>{

	@Query(value="select f from Format f where f.format=:format")
	Format getFormatByName(@Param("format")String format);

	@Query(value="update Format as f set f.format=:updateFormatName where f.format=:format")
	@Modifying
	@Transactional
	int updateFormat(@Param("format")String format, @Param("updateFormatName")String updateFormatName);

}
