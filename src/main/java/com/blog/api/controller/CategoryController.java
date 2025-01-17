package com.blog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.mapper.CategoryDto;
import com.blog.api.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/blog-api/v1/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping(value="/getAllCategorys")
	public ResponseEntity<List<CategoryDto>> getAllEntity(){
		return new ResponseEntity<List<CategoryDto>>(this.categoryService.getAllCategorys(), HttpStatus.OK);
	}
	
	@PostMapping(value="/creteCategory")
	public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto){
	return new ResponseEntity<>(this.categoryService.createCategory(categoryDto), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/updateCategory/{id}")
	public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable ("id") int categoryid){
		return new ResponseEntity<>(this.categoryService.updateCatagory(categoryid, categoryDto), HttpStatus.OK);
	}
	
	@GetMapping("/getCategoryById/{id}")
	public ResponseEntity<?> getMethodName(@PathVariable ("id") int categoryid) {
		return new ResponseEntity<>(this.categoryService.findById(categoryid), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deleteCategoryById/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable ("id") int categoryid) {
		return new ResponseEntity<>(this.categoryService.deleteCategory(categoryid), HttpStatus.OK);
	}
	
	

}
