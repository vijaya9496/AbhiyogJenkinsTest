package com.fg.ss.abhiyog.common.vo;

public class ResetPasswordVO {

	
	private String loginId;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResetPasswordVO [loginId=");
		builder.append(loginId);
		builder.append("]");
		return builder.toString();
	}
	
}
