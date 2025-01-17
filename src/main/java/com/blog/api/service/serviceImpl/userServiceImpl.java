package com.blog.api.service.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entity.User;
import com.blog.api.exception.ResourceNotFoundException;
import com.blog.api.mapper.UserDTO;
import com.blog.api.reposistry.UserReposistry;
import com.blog.api.service.userService;


@Service
public class userServiceImpl implements userService{
	
	@Autowired
	UserReposistry userReposistry;
	@Autowired
	ModelMapper modalMaper;

	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user=modalMaper.map(userDto, User.class);
		userReposistry.save(user);
		return userDto;
	}

	@Override
	public UserDTO UpdateUser(UserDTO userDTO, Integer userid) {
		User user=userReposistry.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User not found with user Id: "+userid));
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setAbout(user.getAbout());
		return modalMaper.map(userReposistry.save(user), UserDTO.class);
	}

	@Override
	public UserDTO getUserById(Integer userid) {
	return modalMaper.map(userReposistry.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User not found with user Id: "+userid)),UserDTO.class);
		
	}

	@Override
	public List<UserDTO> getAllUsers() {
	List<User> userList=userReposistry.findAll();
	return userList.stream().map(s-> modalMaper.map(s, UserDTO.class)).toList();
	}

	@Override
	public Boolean deleteUserById(Integer userid) {
		userReposistry.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User not found with user Id: "+userid));	
	    this.userReposistry.deleteById(userid);
		return true;	
	}

}
 