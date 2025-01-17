package com.blog.api.service;

import java.util.List;

import com.blog.api.mapper.PostDto;

public interface PostService {

	PostDto createPost(PostDto post,int userId,int categoryId);
	
	PostDto updatePost(PostDto post,int postId);
	
	Boolean deletePost(int postId);
	
	List<PostDto> getAllPost();
	
	PostDto getPostById(int postId);
	
	List<PostDto> getAllPostByCategory(int categoryId);
	
	List<PostDto> getAllPostByUser(int userId);
	
}
