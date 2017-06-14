package com.ohb.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.type.Category;

@Repository(value="categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Integer>,
JpaSpecificationExecutor<Category>{
	
	
	@Query(value="select ca from Category ca where ca.name = :name")
	Category findByName(@Param("name") String name);
}
