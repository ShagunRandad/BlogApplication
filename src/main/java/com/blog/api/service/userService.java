package com.blog.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.api.mapper.UserDTO;

@Service
public interface userService {
	 
	UserDTO createUser(UserDTO userDto);
	
	UserDTO UpdateUser(UserDTO userDTO,Integer userid);
	
	UserDTO getUserById(Integer userid);
	
	List<UserDTO> getAllUsers();
	
	Boolean deleteUserById(Integer userid);
	
	
	
	
}
