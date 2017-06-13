package com.ohb.app.repo;

import org.springframework.data.repository.CrudRepository;

import com.ohb.app.model.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {

	Hotel findByName(String name);
}
