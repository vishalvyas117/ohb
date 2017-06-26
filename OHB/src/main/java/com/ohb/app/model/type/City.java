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
@Table(name = "city")
public class City {
	@Id
	@Column(name = "CITY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer city_id;

	@Column(name = "CITY_NAME")
	private String name;

	public City() {
		super();
	}

	@JsonCreator
	public City(@JsonProperty("city_id") Integer cityid, @JsonProperty("city_name") String name) {
		super();
		this.city_id = cityid;
		this.setName(name);
	}


	/**
	 * @return the city_id
	 */
	public Integer getCity_id() {
		return city_id;
	}

	/**
	 * @param city_id the city_id to set
	 */
	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
