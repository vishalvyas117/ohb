package com.ohb.app.model;

import java.io.Serializable;
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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ohb.app.model.type.RoomType;

@Entity
@Table(name = "ROOM")
public class Room implements Serializable, Comparable<Object>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -375712481468043185L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROOM_ID")
	private Integer room_id;

	@NotNull
	@Column(name = "FLOOR")
	private int floor;

	@NotNull
	@Column(name = "ROOM_NUMBER")
	private String room_number;
	
	@JsonIgnore
	@JsonSerialize
	@JsonDeserialize	
	@ManyToOne(optional=false,cascade=CascadeType.MERGE )
	@JoinColumn(name = "room_type_id", referencedColumnName="room_type_id")
	private RoomType type;
	
	@JsonIgnore
	@JsonSerialize
	@JsonDeserialize	
	@ManyToOne(optional=false,cascade=CascadeType.MERGE )
    @JoinColumn(name = "hotel_id", referencedColumnName="hotel_id",updatable=false)
	private Hotel hotel;

	@Column(name = "PRICE")
	private double price;

	@ElementCollection
	@Column(name = "DAYS_RESERVED")
	private Map<String, Integer> dateReserved = new HashMap<String, Integer>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Booking.class)
	private Map<Long, Booking> bookings = new HashMap<Long, Booking>();

	public Room() {
		super();
	}

	public Room(Integer room_Id, int floor, String room_number, RoomType type, Hotel hotel, double price) {
		super();
		this.room_id = room_Id;
		this.floor = floor;
		this.room_number = room_number;
		this.type = type;
		this.hotel = hotel;
		this.setPrice(price);
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

	

	public Integer getRoom_id() {
		return room_id;
	}

	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
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

	public Map<String, Integer> getDays_reserved() {
		return dateReserved;
	}

	public void setDays_reserved(Map<String, Integer> days_reserved) {
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
