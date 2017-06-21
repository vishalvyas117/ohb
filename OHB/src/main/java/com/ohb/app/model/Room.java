package com.ohb.app.model;

import java.io.Serializable;
import javax.persistence.*;

import com.ohb.app.model.type.RoomDateReserved;
import com.ohb.app.model.type.RoomType;

import java.util.List;


/**
 * The persistent class for the room database table.
 * 
 */
@Entity
@Table(name="room")
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="room_id", unique=true, nullable=false)
	private int roomId;

	@Column(nullable=false)
	private int floor;

	private double price;

	@Column(name="room_number", nullable=false, length=255)
	private String roomNumber;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="room")
	private List<Booking> bookings;

	//bi-directional many-to-one association to RoomType
	@ManyToOne
	@JoinColumn(name="room_type_id")
	private RoomType roomType;

	//bi-directional many-to-one association to Hotel
	@ManyToOne
	@JoinColumn(name="hotel_id", nullable=false)
	private Hotel hotel;

	//bi-directional many-to-one association to RoomDateReserved
	@OneToMany(mappedBy="room")
	private List<RoomDateReserved> roomDateReserveds;

	public Room() {
	}

	public int getRoomId() {
		return this.roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getFloor() {
		return this.floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setRoom(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setRoom(null);

		return booking;
	}

	public RoomType getRoomType() {
		return this.roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<RoomDateReserved> getRoomDateReserveds() {
		return this.roomDateReserveds;
	}

	public void setRoomDateReserveds(List<RoomDateReserved> roomDateReserveds) {
		this.roomDateReserveds = roomDateReserveds;
	}

	public RoomDateReserved addRoomDateReserved(RoomDateReserved roomDateReserved) {
		getRoomDateReserveds().add(roomDateReserved);
		roomDateReserved.setRoom(this);

		return roomDateReserved;
	}

	public RoomDateReserved removeRoomDateReserved(RoomDateReserved roomDateReserved) {
		getRoomDateReserveds().remove(roomDateReserved);
		roomDateReserved.setRoom(null);

		return roomDateReserved;
	}

}