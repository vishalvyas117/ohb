package com.ohb.app.model.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "city")
public class City {
	@Id
	@Column(name = "CITY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer city_id;

	@Column(name = "CITY_NAME")
	private String name;
	
	@Column(name = "ISPOPULAR")
	private boolean isPopular;

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

	public boolean isPopular() {
		return isPopular;
	}

	public void setPopular(boolean isPopular) {
		this.isPopular = isPopular;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city_id == null) ? 0 : city_id.hashCode());
		result = prime * result + (isPopular ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (city_id == null) {
			if (other.city_id != null)
				return false;
		} else if (!city_id.equals(other.city_id))
			return false;
		if (isPopular != other.isPopular)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{City {city_id :" + city_id + ", name :" + name + ", isPopular : " + isPopular + "}}";
	}
	
	

}
