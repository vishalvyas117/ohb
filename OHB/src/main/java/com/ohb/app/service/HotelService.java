package com.ohb.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohb.app.model.Hotel;
import com.ohb.app.model.type.Category;
import com.ohb.app.model.type.City;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.repo.UserRepository;
import com.ohb.app.util.TokenizerUtil;

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

	public List<Hotel> findAllforUser(int pageNumber, int pageSize) {
		List<Hotel> page = this.hotelRepository.findAll();

		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> findAll() {
		List<Hotel> page = this.hotelRepository.findAll();

		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> findHotelsByCity(City city) {
		if (city == null) {
			return null;
		}
		List<Hotel> page = this.hotelRepository.findHotelsByCity(city);

		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> findHotelsByAddress(String address) {
		if (address == null) {
			return null;
		}
		Set<String> addr = TokenizerUtil.addressTokenizer(address);
		List<Hotel> page = this.hotelRepository.findHotelsByAddressContains(address);
		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}
	
	public List<Hotel> findHotelsByNameIn(String name) {
		if (name == null) {
			return null;
		}
		Set<String> names = TokenizerUtil.addressTokenizer(name);
		List<Hotel> page = this.hotelRepository.findHotelsByNameContaining(name);
		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> findHotelsByRating(Integer rating) {
		if (rating == null) {
			rating = 0;
		}
		List<Hotel> page = this.hotelRepository.findHotelsByRatingLessThanEqual(rating);
		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> findHotelsByUptoRating(Integer rating) {
		if (rating == null) {
			rating = 0;
		}
		List<Hotel> page = this.hotelRepository.findHotelsByRatingGreaterThanEqual(rating);
		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> findHotelsByCategory(String category_id) {
		if (category_id == null) {
			category_id = "";
		}
		Category category=categoryService.findByName(category_id);
		List<Hotel> page = this.hotelRepository.findHotelsByCategory(category);
		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> fillList(List<Hotel> page) {
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
	/*
	 * public Page<Hotel> fillList(Page<Hotel> page) { Page<Hotel> hotellist =
	 * new Page<Hotel>(); for (Hotel hotel : page) { Hotel dto = new Hotel();
	 * dto.setHotelid(hotel.getHotelid()); dto.setName(hotel.getName());
	 * dto.setRating(hotel.getRating()); dto.setCategory(hotel.getCategory());
	 * dto.setAddress(hotel.getAddress()); dto.setImages(hotel.getImages());
	 * dto.setCity(hotel.getCity()); dto.setRooms(hotel.getRooms());
	 * hotellist.add(dto); } return hotellist; }
	 */

}
