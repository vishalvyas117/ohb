package com.ohb.app.model.type;

import java.io.Serializable;
import javax.persistence.*;

import com.ohb.app.model.Room;


/**
 * The persistent class for the room_date_reserved database table.
 * 
 */
@Entity
@Table(name="room_date_reserved")
@NamedQuery(name="RoomDateReserved.findAll", query="SELECT r FROM RoomDateReserved r")
public class RoomDateReserved implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoomDateReservedPK id;

	@Column(name="days_reserved")
	private int daysReserved;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="room_room_id", nullable=false, insertable=false, updatable=false)
	private Room room;

	public RoomDateReserved() {
	}

	public RoomDateReservedPK getId() {
		return this.id;
	}

	public void setId(RoomDateReservedPK id) {
		this.id = id;
	}

	public int getDaysReserved() {
		return this.daysReserved;
	}

	public void setDaysReserved(int daysReserved) {
		this.daysReserved = daysReserved;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
