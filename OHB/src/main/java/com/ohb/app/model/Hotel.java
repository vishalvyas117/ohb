package com.ohb.app.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ohb.app.model.type.Category;
import com.ohb.app.model.type.City;

@Entity
@Table(name = "HOTEL")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Hotel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1080914434324497890L;
	@Id
	@Column(name = "HOTEL_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer hotel_id;
	
	@Column(name = "HOTEL_NAME")
	@NotNull
	private String name;

	@Column(name = "HOTEL_ADDRESS")
	@NotNull
	private String address;

	@Column(name = "HOTEL_RATING")
	private int rating;
	private boolean status;

	//@JsonIgnore
	@JsonSerialize
	@JsonDeserialize	
	@JsonProperty
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = true)
	private User manager;

	@JsonIgnore
	@JsonSerialize
	@JsonDeserialize	
	@JsonProperty
	@ManyToOne(optional=false)
	@JoinColumn(name = "category_id", referencedColumnName="category_id",nullable=false,updatable=false)
	private Category category;

	//@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "city_id", referencedColumnName = "city_id", nullable = false)
	private City city;

	@JsonIgnore
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private Map<Integer, Room> rooms = new HashMap<Integer, Room>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private Map<Integer, Comment> comments = new HashMap<Integer, Comment>();

	@JsonIgnore
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private Map<Long, Image> images = new HashMap<Long, Image>();

	public Hotel() {
	}

	public Hotel(Integer id, String name, String address, int rating, Category category) {
		this.hotel_id = id;
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.category = category;
	}

	public Integer getHotelid() {
		return hotel_id;
	}

	public void setHotelid(Integer hotelid) {
		this.hotel_id = hotelid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Map<Integer, Room> getRooms() {
		return rooms;
	}

	public void setRooms(Map<Integer, Room> rooms) {
		this.rooms = rooms;
	}

	public Map<Integer, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Integer, Comment> comments) {
		this.comments = comments;
	}

	public Integer getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}

	public Map<Long, Image> getImages() {
		return images;
	}

	public void setImages(Map<Long, Image> images) {
		this.images = images;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
	@Override
    public String toString() {
    	return "HotelID: " + getHotel_id() + "\nName: " + getName() + "\nAddress: " + getAddress() + "\nRating: " + getRating() + "\nCategory: " + category.getName() + "\nManager: " + getManager();
    }

}
