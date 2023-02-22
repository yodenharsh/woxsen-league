package com.woxsen.leaguewebsite.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.woxsen.leaguewebsite.entitiy.misc.ErrorMessage;
import com.woxsen.leaguewebsite.exception.SlotAlreadyBookedException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RESTGlobalExceptionHandler {

	@ExceptionHandler(value = EntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage EntityNotFoundExceptionHandler(EntityNotFoundException e, WebRequest wr) {
		ErrorMessage em = new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND);
		return em;
	}
	
	@ExceptionHandler(value = SlotAlreadyBookedException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ErrorMessage slotAlreadyBookedExceptionHandler(SlotAlreadyBookedException e, WebRequest wr) {
		ErrorMessage em = new ErrorMessage(e.getMessage(), HttpStatus.CONFLICT);
		return em;
	}
}
