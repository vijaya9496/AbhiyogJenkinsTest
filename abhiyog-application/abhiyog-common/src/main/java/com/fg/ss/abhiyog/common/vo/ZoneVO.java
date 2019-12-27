package com.fg.ss.abhiyog.common.vo;

public class ZoneVO {

	private int zoneId;
	private String zoneName;
	private String zoneHead;
	public int getZoneId() {
		return zoneId;
	}
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public String getZoneHead() {
		return zoneHead;
	}
	public void setZoneHead(String zoneHead) {
		this.zoneHead = zoneHead;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ZoneVO [zoneId=");
		builder.append(zoneId);
		builder.append(", zoneName=");
		builder.append(zoneName);
		builder.append(", zoneHead=");
		builder.append(zoneHead);
		builder.append("]");
		return builder.toString();
	}

	

}
