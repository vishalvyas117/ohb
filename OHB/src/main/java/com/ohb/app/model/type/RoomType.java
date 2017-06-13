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
@Table(name = "room_type")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class RoomType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROOM_TYPE_ID")
	private Integer roomtypeid;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "OCCUPANCY")
	private int occupancy;

	public RoomType() {
	}
	
	public RoomType(Integer roomid, String description,int occupancy) {
		this.roomtypeid = roomid;
		this.description = description;
		this.occupancy = occupancy;
	}

	public Integer getRoomid() {
		return roomtypeid;
	}

	public void setRoomid(Integer roomid) {
		this.roomtypeid = roomid;
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
