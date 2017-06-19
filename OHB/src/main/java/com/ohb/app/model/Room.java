package com.ohb.app.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ohb.app.model.type.RoomType;

@Entity
@Table(name = "ROOM")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Room implements Comparable<Object>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROOM_ID")
	private Integer room_id;

	@NotNull
	@Column(name = "FLOOR")
	private int floor;

	@NotNull
	@Column(name = "ROOM_NUMBER")
	private String room_number;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "room_type_id",nullable=false, updatable=true)
	private RoomType type;
	
	@JsonIgnore
	@ManyToOne(optional=false) 
    @JoinColumn(name="hotel_id", nullable=false, updatable=true)
	private Hotel hotel;

	@Column(name = "PRICE")
	private double price;

	@ElementCollection
	@Column(name = "DAYS_RESERVED")
	private Map<Date, Integer> dateReserved = new HashMap<Date, Integer>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Booking.class)
	private Map<Long, Booking> bookings = new HashMap<Long, Booking>();

	public Room() {
		super();
	}

	public Room(Integer roomId, int floor, String room_number, RoomType type, Hotel hotel, double price) {
		super();
		this.room_id = roomId;
		this.floor = floor;
		this.room_number = room_number;
		this.type = type;
		this.hotel = hotel;
		this.setPrice(price);
	}

	public Integer getRoomId() {
		return room_id;
	}

	public void setRoomId(Integer roomId) {
		this.room_id = roomId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Map<Date, Integer> getDays_reserved() {
		return dateReserved;
	}

	public void setDays_reserved(Map<Date, Integer> days_reserved) {
		this.dateReserved = days_reserved;
	}

	public Map<Long, Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Map<Long, Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public int compareTo(Object o) {
		return getRoom_number().compareTo(((Room) o).getRoom_number());
	}

}
