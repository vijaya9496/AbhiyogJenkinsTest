package com.fg.ss.abhiyog.common.vo;

//import org.codehaus.jackson.annotate.JsonProperty;
//import org.codehaus.jackson.map.annotate.JsonSerialize;

//@JsonSerialize

public class BaseResponseVO {
	//making class singleton
	private static BaseResponseVO instance;
	private int responseCode;
	private String responseMessage;
//	@JsonProperty
	private Object data;
	
	private BaseResponseVO() {}
	
	static {
		try {
			instance = new BaseResponseVO();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BaseResponseVO getInstance() {
		return instance;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static void setInstance(BaseResponseVO instance) {
		BaseResponseVO.instance = instance;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseResponseVO [responseCode=");
		builder.append(responseCode);
		builder.append(", responseMessage=");
		builder.append(responseMessage);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
	
	
}
