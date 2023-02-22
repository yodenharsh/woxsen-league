package com.woxsen.leaguewebsite.entitiy;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "slots")
public class Slot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "from")
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime from;
	
	@Column(name = "to")
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime to;
	
	@Column(name = "charge")
	private int charge;
	
	@Column(name = "facility")
	private String facility;
	
	@Column(name = "group")
	private int group;

	public Slot(int id, LocalTime from, LocalTime to, int charge, String facility, int group) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.charge = charge;
		this.facility = facility;
		this.group = group;
	}
	
	public Slot() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalTime getFrom() {
		return from;
	}

	public void setFrom(LocalTime from) {
		this.from = from;
	}

	public LocalTime getTo() {
		return to;
	}

	public void setTo(LocalTime to) {
		this.to = to;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}
	
	
}
