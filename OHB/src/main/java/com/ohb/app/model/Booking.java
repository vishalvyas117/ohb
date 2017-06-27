package com.ohb.app.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "BOOKING")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Booking {

	@Id
	@Column(name = "BOOKING_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer booking_id;

	@Column(name = "BOOKING_BEGIN_DATE")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private String begin_date;

	@Column(name = "BOOKING_END_DATE")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private String end_date;

	@Column(name = "BOOKING_STATUS")
	private boolean state;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = true)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID", nullable = true)
	private Room room;

	public Booking() {
		super();
	}

	public Booking(@JsonProperty("bookingid") Integer bookingid, @JsonProperty("CheckIn") String begin_date,
			@JsonProperty("CheckOut") String end_date, @JsonProperty("available") boolean state,
			@JsonProperty("Customer") User user, @JsonProperty("Room") Room room) {
		super();
		this.booking_id = bookingid;
		this.begin_date = begin_date;
		this.end_date = end_date;
		this.state = state;
		this.user = user;
		this.room = room;
	}

	public Integer getBookingid() {
		return booking_id;
	}

	public void setBookingid(Integer bookingid) {
		this.booking_id = bookingid;
	}

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
