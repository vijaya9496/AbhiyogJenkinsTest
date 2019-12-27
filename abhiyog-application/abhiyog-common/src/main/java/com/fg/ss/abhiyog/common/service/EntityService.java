package com.fg.ss.abhiyog.common.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.EntitySummary;
import com.fg.ss.abhiyog.common.repository.EntityRepository;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.EntityVO;

@Service
public class EntityService implements IEntityService {

	@Autowired
	private EntityRepository entityRepository;

	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@Override
	public List<EntityVO> getAllEntities() {
		List<EntitySummary> entityDtls = entityRepository.findAll();
		if (entityDtls == null) {
			return null;
		}
		return entityDtls.stream().map(entities -> convertToDto(entities)).collect(Collectors.toList());
	}

	private EntityVO convertToDto(EntitySummary entities) {
		EntityVO entityDto = new EntityVO();
		entityDto.setEntityId(entities.getEntityId());
		entityDto.setEntityName(entities.getEntityName());
		entityDto.setEntityHeadId(entities.getEntityHeadId());
		entityDto.setEntityDesc(entities.getEntityDesc());
		return entityDto;
	}

	@Override
	public EntitySummary getEntityByName(String entityName) {
		EntitySummary entity = entityRepository.getEntityByName(entityName);
		return entity;
	}

	@Override
	public BaseResponseVO saveEntityData(String entityName) {
		EntitySummary entity = new EntitySummary();
		entity.setEntityName(entityName);
		entityRepository.save(entity);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("ENTITY ADDED SUCCESSFULLY");
		return baseResponseVO;
	}

	@Override
	public int updateEntityByName(String entityName, String updatedEntityName) {
		EntitySummary entity = entityRepository.getEntityByName(updatedEntityName);
		// Checking UpdatedEntityName Already Existed in DB or not
		if (entity == null) {
			int isUpdated = entityRepository.update(entityName, updatedEntityName);
			return isUpdated;
		}
		return 0;
	}

}
