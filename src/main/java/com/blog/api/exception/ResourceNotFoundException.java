package com.blog.api.exception;



public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(){
		super("Something went wrong");
	}
	
	public ResourceNotFoundException(String message){
		super(message);
	}
}
