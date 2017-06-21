package com.ohb.app.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ohb.app.api.response.StatusResponse;
import com.ohb.app.model.Hotel;
import com.ohb.app.model.Room;
import com.ohb.app.model.type.RoomType;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.service.BookingService;
import com.ohb.app.service.RoomService;
import com.ohb.app.util.api.APIName;
import com.ohb.app.util.api.APIStatus;
import com.ohb.app.util.api.APIUtil;
import com.ohb.app.util.api.Constant;
import com.ohb.app.util.api.DtoUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = APIName.HOTELROOM)
public class RoomController extends APIUtil {

	@Autowired
	RoomTypeRepository roomTypes;

	@Autowired
	RoomRepository rooms;

	@Autowired
	RoomService roomService;
	@Autowired
	HotelRepository hotels;
	@Autowired
	BookingService bookingServise;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "get list of Rooms for perticular hotel", notes = "")
	@RequestMapping(path = APIName.ROOMS, method = RequestMethod.GET, produces = APIName.CHARSET)
	public String showRooms(@PathVariable("hotel_id") Integer id,
			@RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
			@RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Room>hotel_rooms=this.roomService.getRoomsbyhotel(id);
		Map<Integer, Room> rooms = new HashMap<Integer, Room>();
		hotel_rooms.forEach(r->
		rooms.put(Integer.parseInt(r.getRoomNumber()), r)
		);
		List<Room> orderedRooms = new ArrayList<Room>();
		SortedSet<Integer> orderedSet = new TreeSet<Integer>(rooms.keySet());
		for (Integer key : orderedSet)
			orderedRooms.add(rooms.get(key));
		result.put("hotel", orderedRooms.get(0).getHotel());
		result.put("orderedRooms", orderedRooms);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "get list of Rooms for perticular hotel", notes = "")
	@RequestMapping(path = APIName.ROOMS_ID, method = RequestMethod.PUT, produces = APIName.CHARSET)
	public String UpdateRooms(@PathVariable("hotel_id") Integer id,@PathVariable("room_id") Integer room_id,@RequestBody Room room) {
		Map<String, Object> result = new HashMap<String, Object>();
		Hotel hotel=this.hotels.findOne(id);
		Room currentRoom=this.rooms.findOne(room_id);
		room.setHotel(hotel);
		room.setRoomId(room_id);
		currentRoom=this.rooms.save(room);
		currentRoom=this.rooms.findOne(room_id);
		result.put("hotel", currentRoom.getHotel());
		result.put("room", currentRoom);
		result.put("roomtype", currentRoom.getRoomType());
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}
	
	@ApiOperation(value = "save rooms ", notes = "by hotel management")
	@RequestMapping(path = APIName.HOTEL_REGISTER, method = RequestMethod.GET, consumes=APIName.CHARSET,produces = APIName.CHARSET)
	public String getRoom(@PathVariable("hotel_id") Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Hotel hotel = hotels.findOne(id);
		List<RoomType> roomtype=(List<RoomType>) this.roomTypes.findAll();
		result.put("hotel", hotel);
		result.put("room", new Room());
		result.put("roomType",roomtype);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}

	@ApiOperation(value = "save rooms ", notes = "by hotel management")
	@RequestMapping(path = APIName.HOTEL_REGISTER, method = RequestMethod.POST, produces = APIName.CHARSET)
	@ResponseBody
	public String saveRooms(@PathVariable("hotel_id") Integer id, @RequestBody Room room) {
		Map<String, Object> result = new HashMap<String, Object>();
		Hotel hotel = hotels.findOne(id);
		room.setHotel(hotel);
		Room currentroom=new Room();
		RoomType roomtype=new RoomType();
		currentroom=roomService.createRoom(room);
		if(currentroom.getRoomType()!=null){
			roomtype=this.roomTypes.findOne(currentroom.getRoomType().getRoomid());
			currentroom.setRoomType(roomtype);
		}
		currentroom.setHotel(hotel);
		Room updateRoom=DtoUtil.roomDtoUtil(currentroom);
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
		Room room=this.rooms.findOne(room_id);
		Hotel hotel=this.hotels.findOne(room.getHotel().getHotelId());
		room.setHotel(hotel);
		Room updateRoom=DtoUtil.roomDtoUtil(room);
		result.put("room", updateRoom);
		result.put("roomType",this.roomService.getAllRoomType());
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "get list of Rooms for perticular hotel", notes = "")
	@RequestMapping(path = APIName.ROOMS_FLOOR, method = RequestMethod.GET, produces = APIName.CHARSET)
	public String getRoomsbyFloor(@PathVariable("hotel_id") Integer hotel_id,@PathVariable("floor") Integer floor,
			@RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
			@RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Room>hotel_rooms=this.roomService.getRoomsbyFloor(floor, pageNumber, pageSize);
		Map<Integer, Room> rooms = new HashMap<Integer, Room>();
		hotel_rooms.forEach(r->
		rooms.put(Integer.parseInt(r.getRoomNumber()), r)
		);
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
	public String getRoomsbyRoomNumber(@PathVariable("hotel_id") String hotel_id,@PathVariable("room_number") String room_number,
			@RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
			@RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Room>hotel_rooms=this.roomService.getRoomsbyRoomNumber(room_number, pageNumber, pageSize);
		Map<Integer, Room> rooms = new HashMap<Integer, Room>();
		hotel_rooms.forEach(r->
		rooms.put(Integer.parseInt(r.getRoomNumber()), r)
		);
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
	public String getRoomsbydaysReserved(@RequestParam(name="fromDate",defaultValue="") String fromDate, @RequestParam(name="toDate",defaultValue="")String toDate) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Room>hotel_rooms=this.roomService.getRoomsbydaysReserved(fromDate,toDate);
		Map<Integer, Room> rooms = new HashMap<Integer, Room>();
		hotel_rooms.forEach(r->
		rooms.put(Integer.parseInt(r.getRoomNumber()), r)
		);
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
	public String getRoomsbyprice(@PathVariable("hotel_id") String hotel_id,@PathVariable("price") double price) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Room>hotel_rooms=this.roomService.getRoomsbyprice(price);
		Map<Integer, Room> rooms = new HashMap<Integer, Room>();
		hotel_rooms.forEach(r->
		rooms.put(Integer.parseInt(r.getRoomNumber()), r)
		);
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
	public String getRoomsbypriceRange(@RequestParam(name="from",defaultValue="") double from, @RequestParam(name="to",defaultValue="")double to) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Room>hotel_rooms=this.roomService.getRoomsbypriceRange(from,to);
		Map<Integer, Room> rooms = new HashMap<Integer, Room>();
		hotel_rooms.forEach(r->
		rooms.put(Integer.parseInt(r.getRoomNumber()), r)
		);
		List<Room> orderedRooms = new ArrayList<Room>();
		SortedSet<Integer> orderedSet = new TreeSet<Integer>(rooms.keySet());
		for (Integer key : orderedSet)
			orderedRooms.add(rooms.get(key));
		result.put("hotel", orderedRooms.get(0).getHotel());
		result.put("orderedRooms", orderedRooms);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}

}
