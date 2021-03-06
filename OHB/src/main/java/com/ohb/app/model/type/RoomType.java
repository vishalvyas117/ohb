package com.ohb.app.model.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;

@Entity
@Table(name = "room_type")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class RoomType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROOM_TYPE_ID")
	private Integer room_type_id;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "OCCUPANCY")
	private int occupancy;

	public RoomType() {
	}
	
	public RoomType(Integer room_type_id, String description,int occupancy) {
		this.room_type_id = room_type_id;
		this.description = description;
		this.occupancy = occupancy;
	}

	public Integer getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(int occupancy) {
		this.occupancy = occupancy;
	}
	

}
