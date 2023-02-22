package com.woxsen.leaguewebsite.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.woxsen.leaguewebsite.dao.UserOperationsDAO;
import com.woxsen.leaguewebsite.entitiy.Booking;
import com.woxsen.leaguewebsite.entitiy.Slot;
import com.woxsen.leaguewebsite.exception.SlotAlreadyBookedException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

@Repository
public class UserOperationsDAOImpl implements UserOperationsDAO {

	private EntityManager entityManager;

	@Autowired
	public UserOperationsDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String bookFacility(Booking booking) {
		Session session = entityManager.unwrap(Session.class);
		
		boolean available = false;
		
		System.out.println("===========>>>>>>>>>>>"+booking.getTo());
		System.out.println("==========>>>>>>>>>>>"+booking.getFrom());

		
		List<Slot> availableSlots = getAvailableSlots(booking.getFacility(), booking.getFrom().toLocalDate());
		for(Slot slot: availableSlots) {
			if(slot.getFrom().equals(booking.getFrom().toLocalTime()) && slot.getTo().equals(booking.getTo().toLocalTime())) {
				available = true;
			}
		}
		if(!available) throw new SlotAlreadyBookedException("Slot is already booked or facility does not exist");
		
		String token = NanoIdUtils.randomNanoId();
		booking.setToken(token);
		
		session.persist(booking);
		
		return token;
	}

	@Override
	public List<Slot> getAvailableSlots(String facility, LocalDate date) {

		Session session = entityManager.unwrap(Session.class);

		LocalDateTime dateStart = date.atStartOfDay();
		LocalDateTime dateEnd = date.atTime(23, 59, 59);

		SelectionQuery<Booking> query = session.createSelectionQuery(
				"from Booking b where b.from between :start and :end and facility=:facility ", Booking.class);
		query.setParameter("start", dateStart);
		query.setParameter("end", dateEnd);
		query.setParameter("facility", facility);

		List<Booking> bookedSlots = query.getResultList();
		List<Slot> timings = getTimings(facility);

		List<Slot> availableSlots = new ArrayList<>();
		List<Integer> groupTaken = new ArrayList<>();

		for (Booking bookedSlot : bookedSlots) {
			LocalDateTime from = bookedSlot.getFrom();
			LocalDateTime to = bookedSlot.getTo();
			for (Slot timing : timings) {
				if (groupTaken.contains(timing.getGroup()))
					continue;
				if (timing.getFrom().equals(from.toLocalTime()) && timing.getTo().equals(to.toLocalTime())) {
					groupTaken.add(timing.getGroup());
				}
			}
		}

		for (Slot timing : timings) {
			if (!(groupTaken.contains(timing.getGroup())))
				availableSlots.add(timing);
		}

		return availableSlots;

	}

	@Override
	public List<Slot> getTimings(String facility) {
		Session session = entityManager.unwrap(Session.class);

		SelectionQuery<Slot> query = session.createSelectionQuery("from Slot s where s.facility= :facility",
				Slot.class);
		query.setParameter("facility", facility);
		List<Slot> slots = query.getResultList();

		return slots;
	}

	@Override
	public Booking getBooking(String token) {

		Session session = entityManager.unwrap(Session.class);

		Booking booking = session.get(Booking.class, token);

		if (booking == null)
			throw new EntityNotFoundException("No booking associated with token=" + token + " was found");

		return booking;
	}

}
