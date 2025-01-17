package com.blog.api.mapper;

import java.util.Date;

import com.blog.api.entity.Category;
import com.blog.api.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class PostDto {
	private String title;
	private String content;
	private String imageName;
	private CategoryDto category;
	private UserDTO user;
}
