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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.elasticsearch.annotations.Document;

import com.ohb.app.model.type.Category;
import com.ohb.app.model.type.City;

@Entity
@Table(name = "HOTEL")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Document(indexName="hotels",type="hotels", shards = 1, replicas = 0, refreshInterval = "-1")
public class Hotel {

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

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = true)
	private Category category;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "city_id", referencedColumnName = "city_id", nullable = true)
	private City city;

	@OneToMany(mappedBy = "hotel",fetch=FetchType.LAZY, cascade=CascadeType.ALL,targetEntity=Room.class)
	private Map<Long, Room> rooms = new HashMap<Long, Room>();

	@OneToMany(mappedBy = "hotel",fetch=FetchType.LAZY, cascade=CascadeType.ALL,targetEntity=Comment.class)
	private Map<Long, Comment> comment = new HashMap<Long, Comment>();

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> images = new HashSet<String>();

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

	public Map<Long, Room> getRooms() {
		return rooms;
	}

	public void setRooms(Map<Long, Room> rooms) {
		this.rooms = rooms;
	}

	public Map<Long, Comment> getComments() {
		return comment;
	}

	public void setComments(Map<Long, Comment> comments) {
		this.comment = comments;
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
