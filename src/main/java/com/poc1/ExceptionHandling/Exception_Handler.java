package com.poc1.ExceptionHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exception_Handler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String , String> handlerInvalidArgument(MethodArgumentNotValidException ex) {
	Map<String , String> errorMap= new HashMap<>();
	ex.getBindingResult().getFieldErrors().forEach(error ->{
		
		errorMap.put(error.getField(),error.getDefaultMessage());
	
	});
	return errorMap;
	}
}
