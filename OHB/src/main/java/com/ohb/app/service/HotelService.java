package com.ohb.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	public Page<Hotel> findAllforUser(int pageNumber,int pageSize) {
		Page<Hotel> page = (Page<Hotel>) this.hotelRepository.findtop3HotelsforeachCity();

		Page<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public Page<Hotel> findAll(Pageable pageable) {
		Page<Hotel> page = this.hotelRepository.findAll(pageable);

		Page<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public Page<Hotel> findHotelsByCity(City city,int pageNumber,int pageSize) {
		if (city == null) {
			return null;
		}
		Page<Hotel> page = this.hotelRepository.findHotelsByCity(city, new PageRequest(pageNumber, pageSize));

		Page<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public Page<Hotel> findHotelsByAddress(String address,int pageNumber,int pageSize) {
		if (address == null) {
			return null;
		}
		Set<String> addr = TokenizerUtil.addressTokenizer(address);
		Page<Hotel> page = this.hotelRepository.findHotelsByAddressContains(address,new PageRequest(pageNumber, pageSize));
		Page<Hotel> hotellist = fillList(page);
		return hotellist;
	}
	
	public Page<Hotel> findHotelsByNameIn(String name,int pageNumber,int pageSize ) {
		if (name == null) {
			return null;
		}
		Set<String> names = TokenizerUtil.addressTokenizer(name);
		Page<Hotel> page = this.hotelRepository.findHotelsByNameContaining(name,new PageRequest(pageNumber, pageSize));
		Page<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public Page<Hotel> findHotelsByRating(Integer rating,int pageNumber, int pageSize) {
		if (rating == null) {
			rating = 0;
		}
		Page<Hotel> page = this.hotelRepository.findHotelsByRatingLessThanEqual(rating,new PageRequest(pageNumber, pageSize));
		Page<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public Page<Hotel> findHotelsByUptoRating(Integer rating,int pageNumber, int pageSize) {
		if (rating == null) {
			rating = 0;
		}
		Page<Hotel> page = this.hotelRepository.findHotelsByRatingGreaterThanEqual(rating,new PageRequest(pageNumber, pageSize));
		Page<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public Page<Hotel> findHotelsByCategory(String category_id,int pageNumber, int pageSize) {
		if (category_id == null) {
			category_id = "";
		}
		Category category=categoryService.findByName(category_id);
		Page<Hotel> page = this.hotelRepository.findHotelsByCategory(category,new PageRequest(pageNumber, pageSize));
		Page<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public Page<Hotel> fillList(Page<Hotel> page) {
		Page<Hotel> hotellist = page;
		/*for (Hotel hotel : page) {
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
		}*/
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
