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

@Service(value = "hotelService")
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
	
	
	@Autowired
	public HotelService(HotelRepository hotelRepository, CategoryService categoryService, UserRepository userRepository,
			RoomRepository roomRepository, RoomTypeRepository roomTypeRepository) {
		super();
		this.hotelRepository = hotelRepository;
		this.categoryService = categoryService;
		this.userRepository = userRepository;
		this.roomRepository = roomRepository;
		this.roomTypeRepository = roomTypeRepository;
	}

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
		if(hotel.getManager()!= null){
			dto.setManager(userRepository.findOne(hotel.getManager().getUser_id().toString()));
		}
		if(hotel.isStatus()){
			dto.setStatus(hotel.isStatus());
		}
		dto.setCity(hotel.getCity());
		dto.setCategory(hotel.getCategory());
		Hotel outHotel = hotelRepository.save(dto);
 		if (outHotel == null) {
			return null;
		}
		return outHotel;
	}

	public List<Hotel> findAllforUser(int pageNumber, int pageSize) {
		List<Hotel> page = (List<Hotel>) this.hotelRepository.findAll();

		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> findAll() {
		List<Hotel> page = (List<Hotel>) this.hotelRepository.findAll();

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
		List<Hotel> page = this.hotelRepository.findHotelsByNameContains(name);
		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}
	
	public Hotel  checkExsitingHotel(String name,String address) {
		if (name == null) {
			name="";
		}if(address==null){
			address="";
		}
		Hotel page = this.hotelRepository.findOneByNameAndAddress(name,address);
		return page;
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
			dto.setHotel_id(hotel.getHotel_id());
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
