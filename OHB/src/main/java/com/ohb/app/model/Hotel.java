package com.ohb.app.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ohb.app.model.type.Category;
import com.ohb.app.model.type.City;

@Entity
@Table(name = "hotel")
public class Hotel {

	@Id
	@Column(name = "HOTEL_ID")
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer hotelid;
	@Column(name = "HOTEL_NAME")
	private String name;
	
	@Column(name = "HOTEL_ADDRESS")
	private String address;
	
	@Column(name = "HOTEL_RATING")
	private int rating;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID", nullable = true)
	private Category category;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "ROOM_TYPE_ID", referencedColumnName = "ROOM_TYPE_ID", nullable = true)
	private City city;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "HOTEL_ID", referencedColumnName = "HOTEL_ID", nullable = true)
	private Map<Long, Room> rooms = new HashMap<Long, Room>();

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "HOTEL_ID", referencedColumnName = "HOTEL_ID", nullable = true)
	private Map<Long, Comment> comments = new HashMap<Long, Comment>();

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> images = new HashSet<String>();

	public Hotel() {
	}

	public Hotel(Integer id, String name, String address, int rating, Category category) {
		this.hotelid = id;
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.category = category;
	}

	public Integer getHotelid() {
		return hotelid;
	}

	public void setHotelid(Integer hotelid) {
		this.hotelid = hotelid;
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

	public Map<Long, Room> getRooms() {
		return rooms;
	}

	public void setRooms(Map<Long, Room> rooms) {
		this.rooms = rooms;
	}

	public Map<Long, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}

	public Set<String> getImages() {
		return images;
	}

	public void setImages(Set<String> images) {
		this.images = images;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
