package com.fg.ss.abhiyog.common.vo;

public class FormatVO {
	private int formatId;
	private String format;
	private String updatedFormatName;

	public int getFormatId() {
		return formatId;
	}

	public void setFormatId(int formatId) {
		this.formatId = formatId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getUpdatedFormatName() {
		return updatedFormatName;
	}

	public void setUpdatedFormatName(String updatedFormatName) {
		this.updatedFormatName = updatedFormatName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FormatVO [formatId=");
		builder.append(formatId);
		builder.append(", format=");
		builder.append(format);
		builder.append("]");
		return builder.toString();
	}

}
