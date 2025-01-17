package com.blog.api.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import payload.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundException(ResourceNotFoundException ex) {
		return new ResponseEntity<>(new ApiResponce(ex.getMessage(), false),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponce> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		 String errorMessage = ex.getBindingResult()
                 .getFieldErrors()
                 .stream()
                 .map(error -> error.getDefaultMessage())  
                 .collect(Collectors.joining(", "));
		return new ResponseEntity<>(new ApiResponce(errorMessage, false),HttpStatus.NOT_FOUND);
	}

}
