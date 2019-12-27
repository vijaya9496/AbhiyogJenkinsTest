package com.fg.ss.abhiyog.common.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@JsonInclude(Include.NON_EMPTY)
//@JsonIgnoreProperties(ignoreUnknown=true)
public class UnitSummaryVO {

	private String loginId;
	private String entityName;
	private String zoneName;
	private String unitName;
//	private String name;
	private String[] unitHeadNames;
	private List<String> unitHead = new ArrayList<String>();

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

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

	public String[] getUnitHeadNames() {
		return unitHeadNames;
	}

	public void setUnitHeadNames(String[] unitHeadNames) {
		this.unitHeadNames = unitHeadNames;
	}

	public List<String> getUnitHead() {
		return unitHead;
	}

	public void setUnitHead(List<String> unitHead) {
		this.unitHead = unitHead;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnitSummaryVO [loginId=");
		builder.append(loginId);
		builder.append(", entityName=");
		builder.append(entityName);
		builder.append(", zoneName=");
		builder.append(zoneName);
		builder.append(", unitName=");
		builder.append(unitName);
		builder.append(", unitHeadNames=");
		builder.append(Arrays.toString(unitHeadNames));
		builder.append(", unitHead=");
		builder.append(unitHead);
		builder.append("]");
		return builder.toString();
	}

	

}
