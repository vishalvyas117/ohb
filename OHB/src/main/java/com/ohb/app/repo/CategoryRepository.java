package com.ohb.app.repo;

import org.springframework.data.repository.CrudRepository;

import com.ohb.app.model.type.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{

	Category findByName(String name);
}
