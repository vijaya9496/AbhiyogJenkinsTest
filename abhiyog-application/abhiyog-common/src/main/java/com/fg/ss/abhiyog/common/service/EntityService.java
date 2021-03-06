package com.fg.ss.abhiyog.common.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.EntitySummary;
import com.fg.ss.abhiyog.common.repository.EntityRepository;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.EntityVO;
import com.fg.ss.abhiyog.common.vo.FormatVO;

@Service
public class EntityService implements IEntityService {

	@Autowired
	private EntityRepository entityRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@Override
	public List<EntityVO> getAllEntities() {
		List<EntitySummary> entityDtls = entityRepository.findAll();
		if (entityDtls == null) {
			return null;
		}
		Type listType = new TypeToken<List<EntityVO>>(){}.getType();
        List<EntityVO> entityVoList = modelMapper.map(entityDtls, listType);
        return entityVoList;
//		return entityDtls.stream().map(entities -> convertToDto(entities)).collect(Collectors.toList());
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
	public void saveEntityData(String entityName) {
		EntitySummary entity = new EntitySummary();
		entity.setEntityName(entityName);
		entityRepository.save(entity);
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

	@Override
	public String findById(int id) {
		Optional<EntitySummary> entity = entityRepository.findById(id);
		String entityName = entity.get().getEntityName();
		return entityName;
	}

}
