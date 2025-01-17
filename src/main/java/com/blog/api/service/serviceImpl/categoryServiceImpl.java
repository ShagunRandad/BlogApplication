package com.blog.api.service.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entity.Category;
import com.blog.api.exception.ResourceNotFoundException;
import com.blog.api.mapper.CategoryDto;
import com.blog.api.reposistry.CategoryRepo;
import com.blog.api.service.CategoryService;

@Service
public class categoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cate = categoryRepo.save(this.modelMapper.map(categoryDto, Category.class));
		return this.modelMapper.map(cate, CategoryDto.class);
	}

	@Override
	public CategoryDto findById(int categoryId) {
		return this.modelMapper.map(
				this.categoryRepo.findById(categoryId).orElseThrow(
						() -> new ResourceNotFoundException("The Category does not exists having this" + categoryId)),
				CategoryDto.class);

	}

	@Override
	public boolean deleteCategory(int categoryId) {
		this.categoryRepo.findById(categoryId).orElseThrow(
				() -> new ResourceNotFoundException("The Category does not exists having this" + categoryId));
		this.categoryRepo.deleteById(categoryId);
		return true;
	
	}

	@Override
	public CategoryDto updateCatagory(int categoryId, CategoryDto categoryDto) {
		this.categoryRepo.findById(categoryId).orElseThrow(
				() -> new ResourceNotFoundException("The Category does not exists having this" + categoryId));
	Category c=	this.categoryRepo.save(this.modelMapper.map(categoryDto, Category.class));
	return this.modelMapper.map(c, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategorys() {
		return this.categoryRepo.findAll().stream().map(s-> this.modelMapper.map(s, CategoryDto.class)).toList();
	}

}
