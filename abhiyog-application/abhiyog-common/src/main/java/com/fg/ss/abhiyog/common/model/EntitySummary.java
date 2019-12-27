package com.fg.ss.abhiyog.common.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "entity")
public class EntitySummary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "entity_id")
	private int entityId;
	@Column(name = "entity_name")
	private String entityName;
	@Column(name = "entity_head_id")
	private int entityHeadId;
	@Column(name = "entity_desc")
	private String entityDesc;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	private List<Units> units;

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public int getEntityHeadId() {
		return entityHeadId;
	}

	public void setEntityHeadId(int entityHeadId) {
		this.entityHeadId = entityHeadId;
	}

	public String getEntityDesc() {
		return entityDesc;
	}

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	public List<Units> getUnits() {
		return units;
	}

	public void setUnits(List<Units> units) {
		this.units = units;
	}
	
	
}
