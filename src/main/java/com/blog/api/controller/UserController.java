package com.blog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.mapper.UserDTO;
import com.blog.api.service.userService;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.Valid;
import lombok.val;

@RestController
@RequestMapping(value="/blog-api/v1/users")
public class UserController {
	
	@Autowired
	userService usrService;
	
	@PostMapping(value="/create")
	public ResponseEntity<UserDTO> createuser(@Valid @RequestBody UserDTO userDto){
		return ResponseEntity.ok(usrService.createUser(userDto));
	}
	
	@PostMapping(value="/updateUser/{id}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto,@PathVariable ("id") Integer userId){
		return ResponseEntity.ok(usrService.UpdateUser(userDto, userId));
	}
	
	@GetMapping(value="/getUserById/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){
		return ResponseEntity.ok(usrService.getUserById(id));
	}
	
	@GetMapping(value="/getAllUsers")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok(usrService.getAllUsers());
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Boolean> deleteUserById(@PathVariable ("id") Integer id) {
		return ResponseEntity.ok(usrService.deleteUserById(id));
	}
	
	
	

}
