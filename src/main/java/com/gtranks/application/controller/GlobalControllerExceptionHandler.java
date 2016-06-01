package com.gtranks.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gtranks.application.exception.MessageDTO;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageDTO handleClientErrors(Exception ex){
		
		return new MessageDTO(ex);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageDTO handleClientDateError(Exception ex){
		MessageDTO mes = new MessageDTO();
		mes.setMessage("Sprawdź wprowadzone dane");
		return mes;
	}
	
}
