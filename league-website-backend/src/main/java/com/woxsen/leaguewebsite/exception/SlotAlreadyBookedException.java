package com.woxsen.leaguewebsite.exception;

public class SlotAlreadyBookedException extends RuntimeException{
	
	
	
	private static final long serialVersionUID = 1L;

	public SlotAlreadyBookedException(String message) {
		super(message);
	}
}
