package com.woxsen.leaguewebsite.service;

import java.time.LocalDate;
import java.util.List;

import com.woxsen.leaguewebsite.entitiy.Booking;
import com.woxsen.leaguewebsite.entitiy.Slot;

public interface UserOperationsService {
	
	public String bookFacility(Booking booking); //Returns String token
	
	public List<Slot> getAvailableSlots(String facility, LocalDate date);
	
	public List<Slot> getTimings(String facility);
	
	public Booking getBooking(String token);
}
