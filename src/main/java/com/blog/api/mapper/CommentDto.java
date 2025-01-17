package com.blog.api.mapper;

import com.blog.api.entity.Post;
import com.blog.api.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	private int commentId;
	private String comment;
	private UserDTO user;
    private PostDto post;
}
