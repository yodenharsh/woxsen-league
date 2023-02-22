package com.woxsen.leaguewebsite.entitiy;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book-list")
public class Booking {

	@Id
	@Column(name = "token")
	private String token;
	
	@Column(name = "`from`")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime from;
	
	@Column(name = "`to`")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime to;
	
	@Column(name = "facility")
	private String facility;
	
	@Column(name = "user_id")
	private String userId;

	public Booking(String token, LocalDateTime from, LocalDateTime to, String facility, String userId) {
		this.token = token;
		this.from = from;
		this.to = to;
		this.facility = facility;
		this.userId = userId;
	}
	
	public Booking(LocalDateTime from, LocalDateTime to, String facility, String userId) {
		this.from = from;
		this.to = to;
		this.facility = facility;
		this.userId = userId;
	}



	public Booking() {}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public LocalDateTime getTo() {
		return to;
	}

	public void setTo(LocalDateTime to) {
		this.to = to;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
