package com.ohb.app.util.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.ohb.app.model.Room;
import com.ohb.app.repo.RoomTypeRepository;

public class DtoUtil {
	
	@Autowired
	RoomTypeRepository roomtype;
	
	public static Room roomDtoUtil(Room room){
		Room currentRoom=new Room();
		if(room.getRoom_id()!=null){
			currentRoom.setRoom_id(room.getRoom_id());
		}
		if(room.getFloor()>=0){
			room.setFloor(room.getFloor());
		}
		if(room.getRoom_number()!=null){
			currentRoom.setRoom_number(room.getRoom_number());
		}
		if(room.getPrice()>0){
			currentRoom.setPrice(room.getPrice());
		}
		if(room.getType()!=null){
			currentRoom.setType(room.getType());
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
