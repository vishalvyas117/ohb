package com.ohb.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ohb.app.model.Hotel;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.repo.UserRepository;
import com.ohb.app.util.TokenizerUtil;

@Service
public class HotelService {
	@Autowired
	 public HotelRepository hotelRepository;

	@Autowired
	public CategoryService categoryService;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public RoomRepository roomRepository;

	@Autowired
	public RoomTypeRepository roomTypeRepository;

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

	public Page<Hotel> findAllforUser(int pageNumber, int pageSize) {
		Page<Hotel> page = this.hotelRepository.findtop3HotelsforeachCity(new PageRequest(pageNumber, pageSize));
		
		return page;
	}

	public List<Hotel> findAll() {
		List<Hotel> page = this.hotelRepository.findAll();

		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> findHotelsByCity(Integer city_id) {
		if (city_id == null) {
			city_id = 0;
		}
		List<Hotel> page = this.hotelRepository.findHotelsByCity(city_id);

		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> findHotelsByAddress(String address) {
		if (address == null) {
			return null;
		}
		Set<String> addr = TokenizerUtil.addressTokenizer(address);
		List<Hotel> page = this.hotelRepository.findHotelsByAddress(addr);
		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> findHotelsByRating(Integer rating) {
		if (rating == null) {
			rating = 0;
		}
		List<Hotel> page = this.hotelRepository.findHotelsByRating(rating);
		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> findHotelsByUptoRating(Integer rating) {
		if (rating == null) {
			rating = 0;
		}
		List<Hotel> page = this.hotelRepository.findHotelsByUptoRating(rating);
		List<Hotel> hotellist = fillList(page);
		return hotellist;
	}

	public List<Hotel> findHotelsByCategory(Integer rating) {
		if (rating == null) {
			rating = 0;
		}
		List<Hotel> page = this.hotelRepository.findHotelsByCategory(rating);
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
	/*public Page<Hotel> fillList(Page<Hotel> page) {
		Page<Hotel> hotellist = new Page<Hotel>();
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
	}*/

}
