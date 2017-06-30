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
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohb.app.api.response.StatusResponse;
import com.ohb.app.model.Hotel;
import com.ohb.app.model.Room;
import com.ohb.app.model.type.Category;
import com.ohb.app.model.type.City;
import com.ohb.app.model.type.RoomType;
import com.ohb.app.repo.CategoryRepository;
import com.ohb.app.repo.CityRepository;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.service.BookingService;
import com.ohb.app.service.RoomService;
import com.ohb.app.util.ResponsePayLoad;
import com.ohb.app.util.TokenizerUtil;
import com.ohb.app.util.api.APIName;
import com.ohb.app.util.api.APIStatus;
import com.ohb.app.util.api.APIUtil;
import com.ohb.app.util.api.Constant;
import com.ohb.app.util.api.DateUtil;
import com.ohb.app.util.api.DtoUtil;

import io.swagger.annotations.ApiOperation;
import scala.collection.mutable.HashSet;

@RestController
@RequestMapping(value = APIName.HOTEL_ROOM)
public class RoomController extends APIUtil {

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

	/*
	 * @ApiOperation(value = "get list of Rooms for perticular hotel", notes =
	 * "")
	 * 
	 * @RequestMapping(path = APIName.ROOMS, method = RequestMethod.GET,
	 * produces = APIName.CHARSET) public String
	 * showRooms(@PathVariable("hotel_id") Integer hotel_id) { Map<String,
	 * Object> result = new HashMap<String, Object>(); Hotel
	 * hotel=this.hotelRepository.findOne(hotel_id);
	 * List<Room>hotel_rooms=this.roomService.getRoomsbyhotel(hotel);
	 * Map<Integer, Room> rooms = new HashMap<Integer, Room>();
	 * hotel_rooms.forEach(r-> rooms.put(Integer.parseInt(r.getRoom_number()),
	 * r) ); List<Room> orderedRooms = new ArrayList<Room>(); SortedSet<Integer>
	 * orderedSet = new TreeSet<Integer>(rooms.keySet()); for (Integer key :
	 * orderedSet) orderedRooms.add(rooms.get(key)); result.put("hotel",
	 * orderedRooms.get(0).getHotel()); result.put("orderedRooms",
	 * orderedRooms); statusResponse = new
	 * StatusResponse(APIStatus.OK.getCode(), result); return
	 * writeObjectToJson(statusResponse); }
	 */

	@ApiOperation(value = "get list of Rooms for perticular hotel", notes = "")
	@RequestMapping(path = APIName.ROOMS_ID, method = RequestMethod.PUT, produces = APIName.CHARSET)
	public String UpdateRooms(@PathVariable("hotel_id") Integer id, @PathVariable("room_id") Integer room_id,
			@RequestBody Room room) {
		Map<String, Object> result = new HashMap<String, Object>();
		Hotel hotel = this.hotelRepository.findOne(id);
		Room currentRoom = this.rooms.findOne(room_id);
		room.setHotel(hotel);
		room.setRoom_id(room_id);
		currentRoom = this.rooms.save(room);
		currentRoom = this.rooms.findOne(room_id);
		result.put("hotel", currentRoom.getHotel());
		result.put("room", currentRoom);
		result.put("roomtype", currentRoom.getType());
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}

	@ApiOperation(value = "save rooms ", notes = "by hotel management")
	@RequestMapping(path = APIName.HOTEL_REGISTER, method = RequestMethod.GET, consumes = APIName.CHARSET, produces = APIName.CHARSET)
	public String getRoom(@PathVariable("hotel_id") Integer hotel_id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Hotel hotel = hotelRepository.findOne(hotel_id);
		List<RoomType> roomtype = (List<RoomType>) this.roomTypes.findAll();
		result.put("hotel", hotel);
		result.put("room", new Room());
		result.put("roomType", roomtype);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}

	@ApiOperation(value = "save rooms ", notes = "by hotel management")
	@RequestMapping(path = APIName.HOTEL_REGISTER, method = RequestMethod.POST, consumes = APIName.CHARSET, produces = APIName.CHARSET)
	public String saveRooms(@PathVariable("hotel_id") Integer hotel_id, @RequestBody Room room) {
		Map<String, Object> result = new HashMap<String, Object>();
		Hotel hotel = hotelRepository.findOne(hotel_id);
		room.setHotel(hotel);
		Room currentroom = new Room();
		RoomType roomtype = new RoomType();
		currentroom = roomService.createRoom(room);
		if (currentroom.getType() != null) {
			roomtype = this.roomTypes.findOne(currentroom.getType().getRoom_type_id());
			currentroom.setType(roomtype);
		}
		currentroom.setHotel(hotel);
		Room updateRoom = DtoUtil.roomDtoUtil(currentroom);
		result.put("hotel", hotel);
		result.put("room", updateRoom);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "get list of Rooms for perticular hotel", notes = "")
	@RequestMapping(path = APIName.ROOMS_ID, method = RequestMethod.GET, produces = APIName.CHARSET)
	public String showRoom(@PathVariable("room_id") Integer room_id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Room room = this.rooms.findOne(room_id);
		room.setHotel(room.getHotel());
		Room updateRoom = DtoUtil.roomDtoUtil(room);
		result.put("room", updateRoom);
		result.put("roomType", this.roomService.getAllRoomType());
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "get list of Rooms for perticular hotel", notes = "")
	@RequestMapping(path = APIName.ROOMS_FLOOR, method = RequestMethod.GET, produces = APIName.CHARSET)
	public String getRoomsbyFloor(@PathVariable("hotel_id") Integer hotel_id, @PathVariable("floor") Integer floor,
			@RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
			@RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Room> hotel_rooms = this.roomService.getRoomsbyFloor(floor, pageNumber, pageSize);
		Map<Integer, Room> rooms = new HashMap<Integer, Room>();
		hotel_rooms.forEach(r -> rooms.put(Integer.parseInt(r.getRoom_number()), r));
		List<Room> orderedRooms = new ArrayList<Room>();
		SortedSet<Integer> orderedSet = new TreeSet<Integer>(rooms.keySet());
		for (Integer key : orderedSet)
			orderedRooms.add(rooms.get(key));
		result.put("hotel", orderedRooms.get(0).getHotel());
		result.put("orderedRooms", orderedRooms);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}

	@ApiOperation(value = "get list of Rooms for perticular hotel", notes = "")
	@RequestMapping(path = APIName.ROOMS_NUMBER, method = RequestMethod.GET, produces = APIName.CHARSET)
	public String getRoomsbyRoomNumber(@PathVariable("hotel_id") String hotel_id,
			@PathVariable("room_number") String room_number,
			@RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
			@RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Room> hotel_rooms = this.roomService.getRoomsbyRoomNumber(room_number, pageNumber, pageSize);
		Map<Integer, Room> rooms = new HashMap<Integer, Room>();
		hotel_rooms.forEach(r -> rooms.put(Integer.parseInt(r.getRoom_number()), r));
		List<Room> orderedRooms = new ArrayList<Room>();
		SortedSet<Integer> orderedSet = new TreeSet<Integer>(rooms.keySet());
		for (Integer key : orderedSet)
			orderedRooms.add(rooms.get(key));
		result.put("hotel", orderedRooms.get(0).getHotel());
		result.put("orderedRooms", orderedRooms);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}

	@ApiOperation(value = "get list of Rooms for perticular hotel", notes = "")
	@RequestMapping(path = APIName.ROOMS_DAYSRESERVED, method = RequestMethod.GET, produces = APIName.CHARSET)
	public String getRoomsbydaysReserved(@RequestParam(name = "fromDate", defaultValue = "") String fromDate,
			@RequestParam(name = "toDate", defaultValue = "") String toDate) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Room> hotel_rooms = this.roomService.getRoomsbydaysReserved(fromDate, toDate);
		Map<Integer, Room> rooms = new HashMap<Integer, Room>();
		hotel_rooms.forEach(r -> rooms.put(Integer.parseInt(r.getRoom_number()), r));
		List<Room> orderedRooms = new ArrayList<Room>();
		SortedSet<Integer> orderedSet = new TreeSet<Integer>(rooms.keySet());
		for (Integer key : orderedSet)
			orderedRooms.add(rooms.get(key));
		result.put("hotel", orderedRooms.get(0).getHotel());
		result.put("orderedRooms", orderedRooms);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}

	@ApiOperation(value = "get list of Rooms for perticular hotel", notes = "")
	@RequestMapping(path = APIName.ROOMS_PRICE, method = RequestMethod.GET, produces = APIName.CHARSET)
	public String getRoomsbyprice(@PathVariable("hotel_id") String hotel_id, @PathVariable("price") double price) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Room> hotel_rooms = this.roomService.getRoomsbyprice(price);
		Map<Integer, Room> rooms = new HashMap<Integer, Room>();
		hotel_rooms.forEach(r -> rooms.put(Integer.parseInt(r.getRoom_number()), r));
		List<Room> orderedRooms = new ArrayList<Room>();
		SortedSet<Integer> orderedSet = new TreeSet<Integer>(rooms.keySet());
		for (Integer key : orderedSet)
			orderedRooms.add(rooms.get(key));
		result.put("hotel", orderedRooms.get(0).getHotel());
		result.put("orderedRooms", orderedRooms);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}

	@ApiOperation(value = "get list of Rooms for perticular hotel", notes = "")
	@RequestMapping(path = APIName.ROOMS_PRICERANGE, method = RequestMethod.GET, produces = APIName.CHARSET)
	public String getRoomsbypriceRange(@RequestParam(name = "from", defaultValue = "") double from,
			@RequestParam(name = "to", defaultValue = "") double to) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Room> hotel_rooms = this.roomService.getRoomsbypriceRange(from, to);
		Map<Integer, Room> rooms = new HashMap<Integer, Room>();
		hotel_rooms.forEach(r -> rooms.put(Integer.parseInt(r.getRoom_number()), r));
		List<Room> orderedRooms = new ArrayList<Room>();
		SortedSet<Integer> orderedSet = new TreeSet<Integer>(rooms.keySet());
		for (Integer key : orderedSet)
			orderedRooms.add(rooms.get(key));
		result.put("hotel", orderedRooms.get(0).getHotel());
		result.put("orderedRooms", orderedRooms);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
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
		ResponsePayLoad result = new ResponsePayLoad();
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
		result.put("hotels", hotels);
		result.put("hotels_count", hoteles.size());
		result.put("category", this.categoryRepo.findAll());
		result.put("roomType", this.roomTypes.findAll());
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}
	

	private List<String> getDates(String checkIn, String checkOut) {

		List<String> dates = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(DateUtil.toDate(checkIn));

		Date endDate = DateUtil.toDate(checkOut);

		while (calendar.getTime().getTime() <= endDate.getTime()) {
			Date result = calendar.getTime();
			dates.add(DateUtil.toDateString(result, TimeZone.getDefault()));
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}

}
