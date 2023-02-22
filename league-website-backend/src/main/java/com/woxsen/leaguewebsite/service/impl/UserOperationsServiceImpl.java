package com.woxsen.leaguewebsite.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woxsen.leaguewebsite.dao.UserOperationsDAO;
import com.woxsen.leaguewebsite.entitiy.Booking;
import com.woxsen.leaguewebsite.entitiy.Slot;
import com.woxsen.leaguewebsite.service.UserOperationsService;

import jakarta.transaction.Transactional;

@Service
public class UserOperationsServiceImpl implements UserOperationsService {

	private UserOperationsDAO userOperationsDAO;
	
	@Autowired
	public UserOperationsServiceImpl(UserOperationsDAO userOperationsDAO) {
		this.userOperationsDAO = userOperationsDAO;
	}

	@Override
	@Transactional
	public String bookFacility(Booking booking) {
		return userOperationsDAO.bookFacility(booking);
	}

	@Override
	@Transactional
	public List<Slot> getAvailableSlots(String facility, LocalDate date) {
		return userOperationsDAO.getAvailableSlots(facility, date);
	}

	@Override
	@Transactional
	public List<Slot> getTimings(String facility) {
		return userOperationsDAO.getTimings(facility);
	}

	@Override
	@Transactional
	public Booking getBooking(String token) {
		return userOperationsDAO.getBooking(token);
	}

}
