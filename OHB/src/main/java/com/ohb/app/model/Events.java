package com.ohb.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ohb.app.model.type.City;

@Entity
@Table(name = "EVENTS")
public class Events {
	
	@Id
	@Column(name = "EVENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventId;
	
	@Column(name = "EVENT_DETAILS")
	@NotNull
	private String eventdetail;
	
	@Column(name = "EVENT_DATE")
	@NotNull
	private String eventDate;
	
	@Column(name = "EVENT_PLACE")
	private String eventPlace;
	
	@JsonIgnore
	@JsonSerialize
	@JsonDeserialize	
	@ManyToOne(optional=false,cascade=CascadeType.MERGE )
	@JoinColumn(name = "city_id", referencedColumnName = "city_id", nullable = false)
	private City city;

	public Events() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Events(Integer eventId, String eventdetail, String eventDate, String eventPlace, City city) {
		super();
		this.eventId = eventId;
		this.eventdetail = eventdetail;
		this.eventDate = eventDate;
		this.eventPlace = eventPlace;
		this.city = city;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getEventdetail() {
		return eventdetail;
	}

	public void setEventdetail(String eventdetail) {
		this.eventdetail = eventdetail;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventPlace() {
		return eventPlace;
	}

	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 7;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((eventPlace == null) ? 0 : eventPlace.hashCode());
		result = prime * result + ((eventdetail == null) ? 0 : eventdetail.hashCode());
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
		Events other = (Events) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (eventPlace == null) {
			if (other.eventPlace != null)
				return false;
		} else if (!eventPlace.equals(other.eventPlace))
			return false;
		if (eventdetail == null) {
			if (other.eventdetail != null)
				return false;
		} else if (!eventdetail.equals(other.eventdetail))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Events [eventId=" + eventId + ", eventdetail=" + eventdetail + ", eventDate=" + eventDate
				+ ", eventPlace=" + eventPlace + ", city=" + city + "]";
	}
	
	

}
