package com.ohb.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ohb.app.model.Room;
import com.ohb.app.model.type.RoomType;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;

@Service
public class RoomService {

	RoomTypeRepository roomTypeRepository;
	RoomRepository roomRepository;

	@Autowired
	public RoomService(RoomTypeRepository roomTypeRepository, RoomRepository roomRepository) {
		super();
		this.roomTypeRepository = roomTypeRepository;
		this.roomRepository = roomRepository;
	}

	public RoomType createRoomType(RoomType roomtype) {
		RoomType currentRoomType = new RoomType();
		if (roomtype == null) {
			return null;
		}
		currentRoomType = this.roomTypeRepository.save(roomtype);
		if (currentRoomType == null) {
			return null;
		}
		return currentRoomType;
	}

	public List<RoomType> getAllRoomType() {
		List<RoomType> roomtypelist = new ArrayList<RoomType>();
		roomtypelist = this.roomTypeRepository.findAll(sortByRoomTypeName());
		return roomtypelist;
	}

	public Room createRoom(Room room) {
		Room currentroom = new Room();
		if (room == null) {
			return null;
		}
		currentroom = this.roomRepository.save(room);
		if (currentroom == null) {
			return null;
		}
		return currentroom;
	}

	public List<Room> getRoomsbyFloor(int floor, Integer pageNum, Integer pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.ASC, "hotel");
		Page<Room> page = this.roomRepository.getRoomsbyFloor(floor, pageable);

		Long totalCount = page.getTotalElements();
		List<Room> rooms = page.getContent();
		List<Room> roomlist = new ArrayList<Room>();
		for (Room room : rooms) {
			Room dto = new Room();
			dto.setRoomId(room.getRoomId());
			dto.setRoom_number(room.getRoom_number());
			dto.setType(room.getType());
			dto.setPrice(room.getPrice());
			dto.setHotel(room.getHotel());
			dto.setBookings(room.getBookings());
			dto.setDays_reserved(room.getDays_reserved());
			roomlist.add(dto);
		}
		return roomlist;
	}

	public List<Room> getRoomsbyhotel(Integer hotelId) {
		List<Room> page = this.roomRepository.getRoomsbyhotel(hotelId);

		List<Room> roomlist = new ArrayList<Room>();
		for (Room room : page) {
			Room dto = new Room();
			dto.setRoomId(room.getRoomId());
			dto.setRoom_number(room.getRoom_number());
			dto.setType(room.getType());
			dto.setPrice(room.getPrice());
			dto.setHotel(room.getHotel());
			dto.setBookings(room.getBookings());
			dto.setDays_reserved(room.getDays_reserved());
			roomlist.add(dto);
		}
		return roomlist;
	}

	public List<Room> getRoomsbyRoomNumber(String roomNumber, Integer pageNum, Integer pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.ASC, "hotel");
		Page<Room> page = this.roomRepository.getRoomsbyRoomNumber(roomNumber, pageable);
		Long totalCount = page.getTotalElements();
		List<Room> rooms = page.getContent();
		List<Room> roomlist = new ArrayList<Room>();
		for (Room room : rooms) {
			Room dto = new Room();
			dto.setRoomId(room.getRoomId());
			dto.setRoom_number(room.getRoom_number());
			dto.setType(room.getType());
			dto.setPrice(room.getPrice());
			dto.setHotel(room.getHotel());
			dto.setBookings(room.getBookings());
			dto.setDays_reserved(room.getDays_reserved());
			roomlist.add(dto);
		}
		return roomlist;
	}
	
	public List<Room> getRoomsbydaysReserved(Date checkIn,Date checkout) {
		List<Room> page = this.roomRepository.getRoomsbydaysReserved(checkIn,checkout);

		List<Room> roomlist = new ArrayList<Room>();
		for (Room room : page) {
			Room dto = new Room();
			dto.setRoomId(room.getRoomId());
			dto.setRoom_number(room.getRoom_number());
			dto.setType(room.getType());
			dto.setPrice(room.getPrice());
			dto.setHotel(room.getHotel());
			dto.setBookings(room.getBookings());
			dto.setDays_reserved(room.getDays_reserved());
			roomlist.add(dto);
		}
		return roomlist;
	}
	
	public List<Room> getRoomsbyprice(double price) {
		List<Room> page = this.roomRepository.getRoomsbyprice(price);

		List<Room> roomlist = new ArrayList<Room>();
		for (Room room : page) {
			Room dto = new Room();
			dto.setRoomId(room.getRoomId());
			dto.setRoom_number(room.getRoom_number());
			dto.setType(room.getType());
			dto.setPrice(room.getPrice());
			dto.setHotel(room.getHotel());
			dto.setBookings(room.getBookings());
			dto.setDays_reserved(room.getDays_reserved());
			roomlist.add(dto);
		}
		return roomlist;
	}
	
	public List<Room> getRoomsbypriceRange(double minprice,double maxprice) {
		List<Room> page = this.roomRepository.getRoomsbypriceRange(minprice,maxprice);

		List<Room> roomlist = new ArrayList<Room>();
		for (Room room : page) {
			Room dto = new Room();
			dto.setRoomId(room.getRoomId());
			dto.setRoom_number(room.getRoom_number());
			dto.setType(room.getType());
			dto.setPrice(room.getPrice());
			dto.setHotel(room.getHotel());
			dto.setBookings(room.getBookings());
			dto.setDays_reserved(room.getDays_reserved());
			roomlist.add(dto);
		}
		return roomlist;
	}

	private Sort sortByRoomTypeName() {

		return new Sort(Sort.Direction.ASC, "roomtypeid");
	}

}
