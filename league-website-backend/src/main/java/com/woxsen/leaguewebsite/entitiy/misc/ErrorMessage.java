package com.woxsen.leaguewebsite.entitiy.misc;

import org.springframework.http.HttpStatusCode;

public class ErrorMessage {
	
	private String msg;
	private HttpStatusCode httpCode;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public HttpStatusCode getHttpCode() {
		return httpCode;
	}
	public void setHttpCode(HttpStatusCode httpCode) {
		this.httpCode = httpCode;
	}
	public ErrorMessage(String msg, HttpStatusCode httpCode) {
		this.msg = msg;
		this.httpCode = httpCode;
	}

	
	
	
}
