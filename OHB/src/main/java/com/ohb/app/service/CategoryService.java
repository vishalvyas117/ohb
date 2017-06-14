package com.ohb.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohb.app.model.Room;
import com.ohb.app.model.type.Category;
import com.ohb.app.repo.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> findAll() {
		List<Category> page = this.categoryRepository.findAll();
		List<Category> categoryList = new ArrayList<Category>();
		for (Category category : page) {
			Category dto = new Category();
			dto.setCategoryid(category.getCategoryid());
			dto.setName(category.getName());
			categoryList.add(dto);
		}
		return categoryList;
	}

	public Category findByName(String name) {
		Category dto = new Category();
		dto = this.categoryRepository.findByName(name);
		return dto;

	}
}
