package com.blog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.mapper.CommentDto;
import com.blog.api.service.commentService;

import jakarta.servlet.http.HttpServlet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(value="/blog-api/v1/comments")
public class CommentController {
	
	@Autowired
	commentService commentService;
	
	@GetMapping("/getCommentById/{id}")
	public ResponseEntity<?> getMethodName(@PathVariable int id ) {
		return new ResponseEntity<>(this.commentService.getComentById(id), HttpStatus.FOUND);
	}
	
	
	@PostMapping("/postComment/{userId}/{postId}")
	public ResponseEntity<?>  postComment(@RequestBody CommentDto commentDto,@PathVariable int userId, @PathVariable int postId) {
		return new ResponseEntity<>(this.commentService.postComment(commentDto, userId, postId), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteCommentById/{id}")
	public Boolean deleteCommentById(@RequestParam int id) {
		return this.commentService.deleteComment(id);
	}
	
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<List<?>> getUserCommentsById(@PathVariable int id){
		return new ResponseEntity<>(this.commentService.getAllCommentByUserId(id), HttpStatus.FOUND);
	}
	
	@GetMapping("/getAllCommentByPostId/{id}")
	public ResponseEntity<List<?>> getCommentByPostId(@PathVariable int id){
		return new ResponseEntity<>(this.commentService.getAllCommentByPostId(id),HttpStatus.FOUND);
	}
	
	@PutMapping("updateComments/{id}")
	public ResponseEntity<?> updateComment(@PathVariable int id, @RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(this.commentService.updateComment(commentDto, id),HttpStatus.OK);
	}
	
	
	
	
	
   
	
	

}
