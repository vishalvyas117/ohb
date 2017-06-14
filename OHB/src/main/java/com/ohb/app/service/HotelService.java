package com.ohb.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohb.app.repo.HotelRepository;

@Service
public class HotelService {
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	CategoryService categoryService;

}
