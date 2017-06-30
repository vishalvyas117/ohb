package com.ohb.app.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohb.app.api.response.StatusResponse;
import com.ohb.app.model.Base;
import com.ohb.app.model.Booking;
import com.ohb.app.model.Comment;
import com.ohb.app.model.Hotel;
import com.ohb.app.model.Room;
import com.ohb.app.model.User;
import com.ohb.app.model.type.Category;
import com.ohb.app.model.type.City;
import com.ohb.app.repo.CategoryRepository;
import com.ohb.app.repo.CityRepository;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.service.BookingService;
import com.ohb.app.service.RoomService;
import com.ohb.app.util.OhbUtil;
import com.ohb.app.util.ResponsePayLoad;
import com.ohb.app.util.TokenizerUtil;
import com.ohb.app.util.api.APIName;
import com.ohb.app.util.api.APIStatus;
import com.ohb.app.util.api.APIUtil;
import com.ohb.app.util.api.DateUtil;

import io.swagger.annotations.ApiOperation;
import scala.collection.mutable.HashSet;

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
	User user;
	//Base links;
	boolean active=false;
	
	@Value("${app.url}")
	private String href;
	
	@Value("${spring.data.rest.basePath}")
	private String basepath;
 
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String homePage() throws Throwable {
		ResponsePayLoad response = new ResponsePayLoad();
		Map<String,String>links=new HashMap<String,String>();
		response.put("room_type", this.roomTypes.findAll());
		response.put("category",this.categoryRepo.findAll());
		response.put("cities", this.cityRepository.findAll());
		response.put("topCities",this.cityRepository.findCityWithLimit());
		response.put("hotelCount",this.hotelRepository.countHotelsByName());
		System.out.println(href);
		links.put("self", href);
		links.put("signUp", href+APIName.USERS+APIName.USERS_REGISTER);
		links.put("signIn", href+APIName.USERS+APIName.USERS_LOGIN);
		links.put("search", href+APIName.SEARCH);
		/*links.add(linkTo(methodOn(UserController.class).register(user)).withRel("signUp"));
		links.add(linkTo(methodOn(UserController.class).login(user.getEmail(), user.getPassword(),false)).withRel("signIn"));
		links.add(linkTo(methodOn(HomeController.class).showhotelsbySearchParam("checkIn", "checkOut","address",Integer.getInteger("numberRooms"),
				Integer.getInteger("guest"),Integer.getInteger("room_type"),Integer.getInteger("category"))).withRel("search"));
		links.add(linkTo(methodOn(RoomController.class).getRoomsbypriceRange(0.00, 0.00)).withRel("priceRange"));*/
		
		response.put("links", links);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), response);
		return writeObjectToJson(statusResponse);
	}
	
	
	@ApiOperation(value = "get list of Rooms for perticular hotel", notes = "")
	@RequestMapping(path = APIName.ROOMS, method = RequestMethod.GET, produces = APIName.CHARSET)
	public String showhotelsbySearchParam(@RequestParam(name = "checkIn", required = false) String checkIn,
			@RequestParam(name = "checkOut", required = false) String checkout,
			@RequestParam(name = "address", required = false) String address,
			@RequestParam(name = "numberRooms", required = false) Integer numberRooms,
			@RequestParam(name = "guest", required = false) Integer guest,
			@RequestParam(name = "room_type", required = false, defaultValue = "0") Integer room_type,
			@RequestParam(name = "category", required = false, defaultValue = "0") Integer category) {
		ResponsePayLoad response = new ResponsePayLoad();
		List<Hotel> hotels = new ArrayList<Hotel>();
		HashSet<Hotel> hoteles = new HashSet<Hotel>();
		List<Room> roomsentity = new ArrayList<>();
		HashSet<Room> roms = new HashSet<Room>();
		if (address != null) {
			City city = this.cityRepository.findByName(address);
			if (city != null) {
				hotels.addAll(this.hotelRepository.findHotelsByCity(city));
			} else {
				Set<String> addresses = TokenizerUtil.addressTokenizer(address);
				hotels.addAll(this.hotelRepository.findHotelsByNameLike(address));
				if (hotels.size() > 0) {
					hotels.forEach(ho -> hoteles.add(ho));
				}
				hotels.addAll(this.hotelRepository.findHotelsByAddressLike(address));
				if (hotels.size() > 0) {
					hotels.forEach(ho -> hoteles.add(ho));
				}
			}
			if (category > 0) {
				if (hotels.size() > 0) {
					for (Hotel ho : hotels) {
						if (ho.getCategory().getCategory_id() == category)
							hotels.add(ho);
					}
				} else {
					Category cat = this.categoryRepo.findOne(category);
					hotels.addAll(this.hotelRepository.findHotelsByCategory(cat));
					/*if (hotels.size() > 0) {
						hotels.forEach(ho -> hoteles.add(ho));
					}*/
				}
			}
			if (guest > 0) {
				roomsentity = this.rooms.getRoomsByGuestNumber(guest);
			}
			if (room_type > 0) {
				if (roomsentity.size() > 0) {
					for (Room ro : roomsentity) {
						if (ro.getType().getRoom_type_id() == room_type)
							hotels.add(ro.getHotel());
					}
				}
			}
		}
		if (checkIn != null && checkout != null) {
			List<String> dates = getDates(checkIn, checkout);
			for (Room ro : roomsentity) {
				Map<String, Integer> room_bookings = ro.getDays_reserved();
				boolean found = false;
				Iterator<String> itDates = dates.iterator();

				while (itDates.hasNext()) {
					String day = itDates.next();
					if (room_bookings.get(day) != null) {
						found = true;
						break;
					}
				}
				if (!found) {
					hotels.add(ro.getHotel());
				}
			}
		}
		response.put("hotels", hotels);
		response.put("hotels_count", hoteles.size());
		response.put("category", this.categoryRepo.findAll());
		response.put("roomType", this.roomTypes.findAll());
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), response);
		return writeObjectToJson(statusResponse);
	}

	private List<String> getDates(String checkIn, String checkOut) {

		List<String> dates = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(DateUtil.toDate(checkIn));

		Date endDate = DateUtil.toDate(checkOut);

		while (calendar.getTime().getTime() <= endDate.getTime()) {
			Date response = calendar.getTime();
			dates.add(DateUtil.toDateString(response, TimeZone.getDefault()));
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}

}

