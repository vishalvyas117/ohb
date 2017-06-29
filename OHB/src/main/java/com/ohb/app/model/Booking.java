package com.ohb.app.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	@JsonIgnore
	@JsonSerialize
	@JsonDeserialize
	@ManyToOne(optional=false,cascade=CascadeType.MERGE )
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = true)
	private User user;
	
	@JsonIgnore
	@JsonSerialize
	@JsonDeserialize
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade( {org.hibernate.annotations.CascadeType.SAVE_UPDATE,org.hibernate.annotations.CascadeType.DELETE} )
	private Set<Room> room;

	public Booking() {
		super();
	}
	@JsonCreator
	public Booking(@JsonProperty("bookingid") Integer bookingid, @JsonProperty("CheckIn") String begin_date,
			@JsonProperty("CheckOut") String end_date, @JsonProperty("available") boolean state,
			@JsonProperty("Customer") User user, @JsonProperty("Room") Set<Room> room) {
		super();
		this.booking_id = bookingid;
		this.begin_date = begin_date;
		this.end_date = end_date;
		this.state = state;
		this.user = user;
		this.room = room;
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
	public Integer getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(Integer booking_id) {
		this.booking_id = booking_id;
	}
	public Set<Room> getRoom() {
		return room;
	}
	public void setRoom(Set<Room> room) {
		this.room = room;
	}

	

}
