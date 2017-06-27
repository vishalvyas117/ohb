package com.ohb.app.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ohb.app.api.response.StatusResponse;
import com.ohb.app.model.Booking;
import com.ohb.app.model.Comment;
import com.ohb.app.model.Hotel;
import com.ohb.app.model.Room;
import com.ohb.app.model.type.RoomType;
import com.ohb.app.repo.BookingRepository;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.repo.UserRepository;
import com.ohb.app.util.ResponsePayLoad;
import com.ohb.app.util.api.APIName;
import com.ohb.app.util.api.APIStatus;
import com.ohb.app.util.api.APIUtil;
import com.ohb.app.util.api.DateUtil;

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
	public String createBookingbyUser(@PathVariable("room_id") Integer room_id) {
		ResponsePayLoad result=new ResponsePayLoad();
		Room room=this.rooms.findOne(room_id);
		Hotel hotel=room.getHotel();
    	Map<Room, Hotel> rooms_available = new HashMap<Room,Hotel>();
    	rooms_available.put(room, hotel);
    	result.put("room_available", rooms_available);
    	result.put("booking", new Booking());
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
		
	}

	private List<String> getDates(Booking booking) {

		List<String> dates = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(DateUtil.toDate(booking.getBegin_date()));
		 
		Date endDate=DateUtil.toDate(booking.getEnd_date());

		while (calendar.getTime().getTime() <= endDate.getTime()) {
			Date result = calendar.getTime();
			dates.add(DateUtil.toDateString(result, TimeZone.getDefault()));
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}
}
