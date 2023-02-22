package com.woxsen.leaguewebsite.dao;

import java.time.LocalDate;
import java.util.List;

import com.woxsen.leaguewebsite.entitiy.Slot;
import com.woxsen.leaguewebsite.entitiy.Booking;

public interface UserOperationsDAO {
	
	
	public String bookFacility(Booking booking); //Returns String token
	
	public List<Slot> getAvailableSlots(String facility, LocalDate date);
	
	public List<Slot> getTimings(String facility);
	
	public Booking getBooking(String token);
	
}
