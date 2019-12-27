package com.fg.ss.abhiyog.common.dto;

import java.util.ArrayList;
import java.util.List;

public class UnitsSummaryDTO {

	private String entityName;
	private String zoneName;
	private String unitName;
	private List<String> unitHead = new ArrayList<String>();

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public List<String> getUnitHead() {
		return unitHead;
	}

	public void setUnitHead(List<String> unitHead) {
		this.unitHead = unitHead;
	}

}
