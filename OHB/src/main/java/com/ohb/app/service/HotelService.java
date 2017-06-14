package com.ohb.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohb.app.model.Hotel;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.repo.UserRepository;

@Service
public class HotelService {
	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	CategoryService categoryService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	RoomTypeRepository roomTypeRepository;

	public Hotel createHotel(Hotel hotel) {
		Hotel dto = new Hotel();
		if (hotel.getAddress() != null) {
			dto.setAddress(hotel.getAddress());
		}
		if (hotel.getName() != null) {
			dto.setName(hotel.getName());
		}
		if (hotel.getRating() > 0) {
			dto.setRating(hotel.getRating());
		} else {
			dto.setRating(1);
		}
		dto.setCity(hotel.getCity());
		dto.setCategory(hotel.getCategory());
		Hotel outHotel = this.hotelRepository.save(dto);
		if (outHotel == null) {
			return null;
		}
		return outHotel;
	}

	public List<Hotel> findAllforUser() {
		List<Hotel> page = this.hotelRepository.findtop3HotelsforeachCity();

		List<Hotel> hotellist = new ArrayList<Hotel>();
		for (Hotel hotel : page) {
			Hotel dto = new Hotel();
			dto.setHotelid(hotel.getHotelid());
			dto.setName(hotel.getName());
			dto.setRating(hotel.getRating());
			dto.setCategory(hotel.getCategory());
			dto.setAddress(hotel.getAddress());
			dto.setImages(hotel.getImages());
			dto.setCity(hotel.getCity());
			dto.setRooms(hotel.getRooms());
			hotellist.add(dto);
		}
		return hotellist;
	}

	public List<Hotel> findAll() {
		List<Hotel> page = this.hotelRepository.findAll();

		List<Hotel> hotellist = new ArrayList<Hotel>();
		for (Hotel hotel : page) {
			Hotel dto = new Hotel();
			dto.setHotelid(hotel.getHotelid());
			dto.setName(hotel.getName());
			dto.setRating(hotel.getRating());
			dto.setCategory(hotel.getCategory());
			dto.setAddress(hotel.getAddress());
			dto.setImages(hotel.getImages());
			dto.setCity(hotel.getCity());
			dto.setRooms(hotel.getRooms());
			hotellist.add(dto);
		}
		return hotellist;
	}

	public List<Hotel> findHotelsByCity(Integer city_id) {
		if(city_id==null){
			city_id=0;
		}
		List<Hotel> page = this.hotelRepository.findHotelsByCity(city_id);

		List<Hotel> hotellist = new ArrayList<Hotel>();
		for (Hotel hotel : page) {
			Hotel dto = new Hotel();
			dto.setHotelid(hotel.getHotelid());
			dto.setName(hotel.getName());
			dto.setRating(hotel.getRating());
			dto.setCategory(hotel.getCategory());
			dto.setAddress(hotel.getAddress());
			dto.setImages(hotel.getImages());
			dto.setCity(hotel.getCity());
			dto.setRooms(hotel.getRooms());
			hotellist.add(dto);
		}
		return hotellist;
	}

}
