package com.blog.api.service.serviceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entity.Category;
import com.blog.api.entity.Post;
import com.blog.api.entity.User;
import com.blog.api.exception.ResourceNotFoundException;
import com.blog.api.mapper.PostDto;
import com.blog.api.reposistry.CategoryRepo;
import com.blog.api.reposistry.PostReposistry;
import com.blog.api.reposistry.UserReposistry;
import com.blog.api.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostReposistry postReposistry;
	@Autowired
	ModelMapper modalMapper;
	@Autowired
	UserReposistry userRepo;
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		Post post = new Post();
		this.modalMapper.map(postDto, post);
		post.setUser(this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with user Id: " + userId)));
		post.setCategory(this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found having this id " + categoryId)));
		post.setDate(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		post.setImageName("default.png");
		return this.modalMapper.map(this.postReposistry.save(post), PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto post, int postId) {
		Post p=this.modalMapper.map(post, Post.class);
		p.setPostId(postId);
		return this.modalMapper.map(this.postReposistry.save(p), PostDto.class);

	}

	@Override
	public Boolean deletePost(int postId) {
		this.postReposistry.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("The Post is not found having this id " + postId));
		this.postReposistry.deleteById(postId);
		return true;
	}

	@Override
	public List<PostDto> getAllPost() {
		return this.postReposistry.findAll().stream().map(s -> this.modalMapper.map(s, PostDto.class)).toList();
	}

	@Override
	public PostDto getPostById(int postId) {
		return this.modalMapper.map(
				this.postReposistry.findById(postId).orElseThrow(
						() -> new ResourceNotFoundException("The Post is not found having this id " + postId)),
				PostDto.class);
	}

	@Override
	public List<PostDto> getAllPostByCategory(int categoryId) {
	Category category=this.categoryRepo.findById(categoryId)
			.orElseThrow(() -> new ResourceNotFoundException("Category not found having this id " + categoryId));
		return this.postReposistry.findByCategory(category).stream().map(s-> this.modalMapper.map(s, PostDto.class)).toList();
	}

	@Override
	public List<PostDto> getAllPostByUser(int userId) {
		User user=this.userRepo.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User not found with user Id: " + userId));
		return	this.postReposistry.findByUser(user).stream().map(s-> this.modalMapper.map(s, PostDto.class)).toList();
	}

}
