package com.ohb.app.repo;

import org.springframework.data.repository.CrudRepository;

import com.ohb.app.model.type.City;

public interface CityRepository extends CrudRepository<City, Integer> {
	City findByName(String name);
}
