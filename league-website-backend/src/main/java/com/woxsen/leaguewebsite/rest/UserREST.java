package com.woxsen.leaguewebsite.rest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woxsen.leaguewebsite.entitiy.Booking;
import com.woxsen.leaguewebsite.entitiy.Slot;
import com.woxsen.leaguewebsite.service.UserOperationsService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserREST {
	
	private UserOperationsService userOperationsService;

	@Autowired
	public UserREST(UserOperationsService userOperationsService) {
		this.userOperationsService = userOperationsService;
	}
	
	@CrossOrigin
	@GetMapping("/userOps/timings/{facility}")
	public List<Slot> getTimings(@PathVariable String facility) {
		return userOperationsService.getTimings(facility);
	}
	
	@CrossOrigin
	@GetMapping("/userOps/booking/{token}")
	public Booking getBooking(@PathVariable String token) {
		return userOperationsService.getBooking(token);
	}
	
	@GetMapping("/userOps/slots")
	@CrossOrigin()
	public List<Slot> getAvailableSlots(@RequestParam("facility") String facility, @RequestParam("date") LocalDate date){
		return userOperationsService.getAvailableSlots(facility, date);
	}
	
	@PostMapping(value = "/userOps/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<HashMap<String, String>> bookSlot(@RequestBody Booking booking){
		String token = userOperationsService.bookFacility(booking);
		HashMap<String,String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		
		return ResponseEntity.ok(tokenMap);
	}
}
