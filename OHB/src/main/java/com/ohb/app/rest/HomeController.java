package com.ohb.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ohb.app.api.response.StatusResponse;
import com.ohb.app.model.Booking;
import com.ohb.app.model.Comment;
import com.ohb.app.model.Hotel;
import com.ohb.app.model.User;
import com.ohb.app.repo.CategoryRepository;
import com.ohb.app.repo.CityRepository;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.service.BookingService;
import com.ohb.app.service.RoomService;
import com.ohb.app.util.OhbUtil;
import com.ohb.app.util.ResponsePayLoad;
import com.ohb.app.util.api.APIStatus;
import com.ohb.app.util.api.APIUtil;

@RestController
public class HomeController extends APIUtil{
	
	
	@Autowired
	RoomTypeRepository roomTypes;

	@Autowired
	RoomRepository rooms;

	@Autowired
	RoomService roomService;
	@Autowired
	HotelRepository hotelRepository;
	@Autowired
	BookingService bookingServise;
	@Autowired
	CityRepository cityRepository;

	@Autowired
	CategoryRepository categoryRepo;
 
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String homePage() throws Throwable {
		ResponsePayLoad result = new ResponsePayLoad();
		
		
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}
}
