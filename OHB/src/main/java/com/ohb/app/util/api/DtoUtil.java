package com.ohb.app.util.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.ohb.app.model.Room;
import com.ohb.app.repo.RoomTypeRepository;

public class DtoUtil {
	
	@Autowired
	RoomTypeRepository roomtype;
	
	public static Room roomDtoUtil(Room room){
		Room currentRoom=new Room();
		if(room.getRoomId()>0){
			currentRoom.setRoomId(room.getRoomId());
		}
		if(room.getFloor()>=0){
			room.setFloor(room.getFloor());
		}
		if(room.getRoomNumber()!=null){
			currentRoom.setRoomNumber(room.getRoomNumber());
		}
		if(room.getPrice()>0){
			currentRoom.setPrice(room.getPrice());
		}
		if(room.getRoomType()!=null){
			currentRoom.setRoomType(room.getRoomType());
		}
		if(room.getHotel()!=null){
			currentRoom.setHotel(room.getHotel());
		}
		if(room.getBookings()!=null){
			currentRoom.setBookings(room.getBookings());
		}
		return currentRoom;
	}

}
