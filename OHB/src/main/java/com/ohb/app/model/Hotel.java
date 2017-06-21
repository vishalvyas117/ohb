package com.ohb.app.model;

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

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the hotel database table.
 * 
 */
@Entity
@NamedQuery(name="Hotel.findAll", query="SELECT h FROM Hotel h")
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="hotel_id")
	private int hotelId;

	@Column(name="hotel_address")
	private String hotelAddress;

	@Column(name="hotel_name")
	private String hotelName;

	@Column(name="hotel_rating")
	private int hotelRating;

	private byte status;

	//bi-directional many-to-one association to HotelCategory
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category hotelCategory;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to Image
	@OneToMany(mappedBy="hotel", fetch=FetchType.EAGER)
	private Set<Image> images;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="hotel", fetch=FetchType.EAGER)
	private Set<Comment> reviews;

	//bi-directional many-to-one association to Room
	@OneToMany(mappedBy="hotel", fetch=FetchType.EAGER)
	private Set<Room> rooms;

	public Hotel() {
	}

	public int getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelAddress() {
		return this.hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public String getHotelName() {
		return this.hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getHotelRating() {
		return this.hotelRating;
	}

	public void setHotelRating(int hotelRating) {
		this.hotelRating = hotelRating;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Category getHotelCategory() {
		return this.hotelCategory;
	}

	public void setHotelCategory(Category hotelCategory) {
		this.hotelCategory = hotelCategory;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Image> getImages() {
		return this.images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public Image addImage(Image image) {
		getImages().add(image);
		image.setHotel(this);

		return image;
	}

	public Image removeImage(Image image) {
		getImages().remove(image);
		image.setHotel(null);

		return image;
	}

	public Set<Comment> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Comment> reviews) {
		this.reviews = reviews;
	}

	public Comment addReview(Comment review) {
		getReviews().add(review);
		review.setHotel(this);

		return review;
	}

	public Comment removeReview(Comment review) {
		getReviews().remove(review);
		review.setHotel(null);

		return review;
	}

	public Set<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Room addRoom(Room room) {
		getRooms().add(room);
		room.setHotel(this);

		return room;
	}

	public Room removeRoom(Room room) {
		getRooms().remove(room);
		room.setHotel(null);

		return room;
	}

}