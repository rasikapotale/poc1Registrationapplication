package com.poc1.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.poc1.ExceptionClass.PanAdharException;
import com.poc1.ExceptionClass.StatusNotSupportedException;


@RestControllerAdvice
public class ApplicationExceptionHandler {


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String, String> errorMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PanAdharException.class)
	public ResponseEntity<String> userNotFound(PanAdharException e){
		return new ResponseEntity<String>("Pan Card or Adhar Card Not Valid",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StatusNotSupportedException.class)
	public ResponseEntity<String> statusNotSupport(StatusNotSupportedException e){
		return new ResponseEntity<String>("Status or status format not found",HttpStatus.NOT_FOUND);
	}
	
}
