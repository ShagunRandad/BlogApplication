package com.blog.api.service.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entity.Comment;
import com.blog.api.entity.Post;
import com.blog.api.entity.User;
import com.blog.api.exception.ResourceNotFoundException;
import com.blog.api.mapper.CommentDto;
import com.blog.api.reposistry.PostReposistry;
import com.blog.api.reposistry.UserReposistry;
import com.blog.api.reposistry.commentsRepo;
import com.blog.api.service.commentService;

@Service
public class commentServiceImpl implements commentService{
	
	@Autowired
	commentsRepo Commentsrepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	PostReposistry postReposistry;
	@Autowired
	UserReposistry userReposistry;

	@Override
	public CommentDto postComment(CommentDto commentDto,int userId,int postId ) {
		Comment comment=new Comment();
	User user=this.userReposistry.findById(userId).orElseThrow (()-> new ResourceNotFoundException("No User Present of this Id : "+userId));
	Post post=this.postReposistry.findById(postId).orElseThrow(()-> new ResourceNotFoundException("No Post is Present of this Id : "+ postId));
	this.modelMapper.map(commentDto, comment);
	comment.setUser(user);
	comment.setPost(post);
		return this.modelMapper.map(this.Commentsrepo.save(comment), CommentDto.class);
	}

	@Override
	public Boolean deleteComment(int commentId) {
			this.Commentsrepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("No Comments are present of this id: "+commentId));
			this.Commentsrepo.deleteById(commentId);
			return true;
	}

	@Override
	public List<CommentDto> getAllCommentByPostId(int postId) {
		Post post=this.postReposistry.findById(postId).orElseThrow(()-> new ResourceNotFoundException("No Posts are present of this id: "+postId));
		return this.Commentsrepo.findByPost(post).stream().map(s->this.modelMapper.map(s, CommentDto.class)).toList();
	}

	@Override
	public List<CommentDto> getAllCommentByUserId(int userId) {
		User user=userReposistry.findById(userId).orElseThrow(()-> new ResourceNotFoundException("No Users are present of this id: "+userId));
		return this.Commentsrepo.findByUser(user).stream().map(s-> this.modelMapper.map(s, CommentDto.class)).toList();
	}

	@Override
	public CommentDto getComentById(int commentId) {
		return this.modelMapper.map(
				this.Commentsrepo.findById(commentId).orElseThrow(
						() -> new ResourceNotFoundException("No Comments are present of this id: " + commentId)),
				CommentDto.class);

	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, int commentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
