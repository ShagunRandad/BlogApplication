package com.blog.api.service;

import java.util.List;

import com.blog.api.mapper.CommentDto;

public interface commentService {
	
	CommentDto postComment(CommentDto commentDto,int userId,int postId);
	
	Boolean deleteComment(int commentId);
	
	List<CommentDto> getAllCommentByPostId(int postId);
	
    List<CommentDto> getAllCommentByUserId(int userId);
    
    CommentDto getComentById(int commentId);
    
    CommentDto updateComment(CommentDto commentDto,int commentId);
	

}
