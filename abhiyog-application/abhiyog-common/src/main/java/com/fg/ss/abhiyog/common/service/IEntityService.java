package com.fg.ss.abhiyog.common.service;

import java.util.List;
import java.util.Optional;

import com.fg.ss.abhiyog.common.model.EntitySummary;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.EntityVO;



public interface IEntityService {

	List<EntityVO> getAllEntities();

	EntitySummary getEntityByName(String entityName);

	void saveEntityData(String entityName);

	int updateEntityByName(String entityName, String updatedEntityName);

	String findById(int id);

}
