package com.blog.api.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.api.mapper.PostDto;
import com.blog.api.service.FileUploadService;
import com.blog.api.service.PostService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import payload.ApiResponce;

@RestController
@RequestMapping(value="/blog-api/v1/post")
public class PostController {
	@Autowired
	PostService postService;
	@Autowired
	FileUploadService fileService;
	@Value("${project.image}")
	private String path;

	@PostMapping("/create")
	public ResponseEntity<?> postMethodName(@RequestBody PostDto postDto,@RequestParam ("userId") int userId,@RequestParam ("categoryId") int categoryId) {
		return	new ResponseEntity<>(this.postService.createPost(postDto, userId, categoryId), HttpStatus.CREATED);
	}
	
	@GetMapping(value="/getAllPosts")
	public ResponseEntity<?> getAllPosts(){
		return	new ResponseEntity<>(this.postService.getAllPost(),HttpStatus.FOUND);
	}
	
	@GetMapping("/getPostsById/{id}")
	public ResponseEntity<?> getMethodName(@PathVariable ("id") int postId) {
		return new ResponseEntity<>(this.postService.getPostById(postId),HttpStatus.FOUND);
	}
	
	@GetMapping("/getPostByUserId/{id}")
	public ResponseEntity<List<?>> getPostByUserId(@PathVariable ("id") int userId){
		return new ResponseEntity<>(this.postService.getAllPostByUser(userId), HttpStatus.FOUND);
	}
	
	@GetMapping("/getPostByCategoryId/{id}")
	public ResponseEntity<List<?>> getPostByCategoryId(@PathVariable ("id") int categoryId) {
		 return new ResponseEntity<>(this.postService.getAllPostByCategory(categoryId),HttpStatus.FOUND);
	}
	
	@DeleteMapping(value="deletePostById/{id}")
	public Boolean deletePost(@PathVariable ("id") int postId) {
		return this.postService.deletePost(postId);
	}
	
	@PostMapping("/image/upload/{id}")
	public ResponseEntity<?> postMethodName(@RequestParam("image") MultipartFile multipartFile,@PathVariable int id) throws IOException {
		PostDto postDto=this.postService.getPostById(id);
		String fileName=this.fileService.uploadImage(path, multipartFile);
		postDto.setImageName(fileName);
	PostDto p=this.postService.updatePost(postDto, id);
		return new ResponseEntity<>(p,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/profiles/{imageName}")
	public void getMethodName(@PathVariable ("imageName") String imageName,HttpServletResponse responce) throws FileNotFoundException {
		InputStream is=this.fileService.getResources(path, imageName);
		responce.setContentType(MediaType.IMAGE_PNG_VALUE);
		
	}
	
	
}
  