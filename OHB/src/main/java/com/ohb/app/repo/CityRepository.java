package com.ohb.app.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.type.City;

@Repository(value="cityRepository")
public interface CityRepository extends CrudRepository<City, Integer> {
	City findByName(String name);
}
