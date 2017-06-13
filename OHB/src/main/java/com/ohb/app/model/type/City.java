package com.ohb.app.model.type;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ohb.app.model.Hotel;

@Entity
@Table(name="city")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cityid;
	@Column(name="city_name")
	@JsonIgnore
	private String name;
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private Set<Hotel> hotels;
	
	
	
	public City() {
		super();
	}
	@JsonCreator
	public City(@JsonProperty("cityid")Integer cityid, @JsonProperty("city_name")String name) {
		super();
		this.cityid = cityid;
		this.setName(name); 
	}
	public Integer getCityid() {
		return cityid;
	}
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Hotel> getHotels() {
		return hotels;
	}
	public void setHotels(Set<Hotel> hotels) {
		this.hotels = hotels;
	}
	
}
