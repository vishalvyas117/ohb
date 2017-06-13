package com.ohb.app.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ohb.app.model.type.RoomType;

@Entity
@Table(name="room")
public class Room {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="room_id")
	private Integer roomId;
	
	@Column(name="floor")
	private int floor;	
	
	@Column(name="room_number")
	private String room_number;	
	
	@ManyToOne
	private RoomType type;
	
	@ManyToOne
	private Hotel hotel;
	
	private int price;
	
	@ElementCollection
	private Map<Date, Integer> days_reserved = new HashMap<Date, Integer>();
	
	 @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy="room")
	 @MapKeyColumn(name="roomid")
	 private Map<Long, Booking> bookings = new HashMap<Long, Booking>();

	public Room() {
		super();
	}

	public Room(Integer roomId, int floor, String room_number, RoomType type, Hotel hotel, int price) {
		super();
		this.roomId = roomId;
		this.floor = floor;
		this.room_number = room_number;
		this.type = type;
		this.hotel = hotel;
		this.setPrice(price);
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getRoom_number() {
		return room_number;
	}

	public void setRoom_number(String room_number) {
		this.room_number = room_number;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Map<Date, Integer> getDays_reserved() {
		return days_reserved;
	}

	public void setDays_reserved(Map<Date, Integer> days_reserved) {
		this.days_reserved = days_reserved;
	}

	public Map<Long, Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Map<Long, Booking> bookings) {
		this.bookings = bookings;
	}
	 
	 
}
