package com.blog.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.api.mapper.CategoryDto;
@Service
public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto findById(int categoryId);
	
	public boolean deleteCategory(int categoryId);
	
	public CategoryDto updateCatagory(int categoryId,CategoryDto categoryDto);
	
	List<CategoryDto> getAllCategorys();
	
}
