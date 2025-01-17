package com.blog.api.reposistry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entity.Comment;
import com.blog.api.entity.Post;
import com.blog.api.entity.User;

public interface commentsRepo extends JpaRepository<Comment, Integer> {
	List<Comment> findByUser(User user);

	List<Comment> findByPost(Post post);
}
