package com.examples.projectorganizerapi.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
	    List<String> messages = new ArrayList<String>();
	    for (FieldError message : ex.getBindingResult().getFieldErrors()) {
	    	messages.add(message.getDefaultMessage());
	    }
	    for (ObjectError message : ex.getBindingResult().getGlobalErrors()) {
	        messages.add(message.getDefaultMessage());
	    }
	     
	    ApiError apiError = 
	      new ApiError(HttpStatus.BAD_REQUEST, messages);
	    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	 public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
	        logger.error("500 Status Code", ex);
	        List<String> messages = new ArrayList<String>();
	        ApiError apiError = 
	      	      new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, messages);
	        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
	 }
	 
	 @ExceptionHandler(value = { EntityNotFoundException.class})
	    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
		 List<String> messages = new ArrayList<String>();
	     messages.add("Resource not found");
	     ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, messages);
	        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	    }
	
	

}
