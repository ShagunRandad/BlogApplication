package com.blog.api.reposistry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entity.Category;
import com.blog.api.entity.Post;
import com.blog.api.entity.User;

public interface PostReposistry extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
}
