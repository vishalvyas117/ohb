package com.ohb.app.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ohb.app.api.response.StatusResponse;
import com.ohb.app.model.Comment;
import com.ohb.app.model.Hotel;
import com.ohb.app.model.Room;
import com.ohb.app.repo.BookingRepository;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.repo.UserRepository;
import com.ohb.app.util.ResponsePayLoad;
import com.ohb.app.util.api.APIName;
import com.ohb.app.util.api.APIStatus;
import com.ohb.app.util.api.APIUtil;

@RestController
@RequestMapping(APIName.BOOKINGS)
@SessionAttributes({"booking", "numberRooms","roomType"})
public class BookingController extends APIUtil{
	
	@Autowired
	BookingRepository bookings;

	@Autowired
	HotelRepository hotels;

	@Autowired
	RoomRepository rooms;

	@Autowired
	UserRepository users;

	@Autowired
	RoomTypeRepository roomTypes;
	
	
	@RequestMapping(path = APIName.BOOKINGS_BY_CREATE, method = RequestMethod.GET, produces = APIName.CHARSET)
	public String createBookingbyUser(@PathVariable("hotel_id") Integer hotel_id) {
		ResponsePayLoad result=new ResponsePayLoad();
		Hotel hotel=this.hotels.findOne(hotel_id);
		Set<Room> room =(Set<Room>) this.rooms.findByHotel(hotel);
		
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
		
	}
}
