package com.fg.ss.abhiyog.common.vo;

public class EntityVO {

	private String entityName;
	private String updatedEntityName;
	private int entityId;
	private int entityHeadId;
	private String entityDesc;
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getUpdatedEntityName() {
		return updatedEntityName;
	}
	public void setUpdatedEntityName(String updatedEntityName) {
		this.updatedEntityName = updatedEntityName;
	}
	
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EntityVO [entityName=");
		builder.append(entityName);
		builder.append(", updatedEntityName=");
		builder.append(updatedEntityName);
		builder.append(", entityId=");
		builder.append(entityId);
		builder.append(", entityHeadId=");
		builder.append(entityHeadId);
		builder.append(", entityDesc=");
		builder.append(entityDesc);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
