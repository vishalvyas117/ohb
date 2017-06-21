package com.ohb.app.model.type;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the room_date_reserved database table.
 * 
 */
@Embeddable
public class RoomDateReservedPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="room_room_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int roomRoomId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_reserved_key", unique=true, nullable=false)
	private java.util.Date dateReservedKey;

	public RoomDateReservedPK() {
	}
	public int getRoomRoomId() {
		return this.roomRoomId;
	}
	public void setRoomRoomId(int roomRoomId) {
		this.roomRoomId = roomRoomId;
	}
	public java.util.Date getDateReservedKey() {
		return this.dateReservedKey;
	}
	public void setDateReservedKey(java.util.Date dateReservedKey) {
		this.dateReservedKey = dateReservedKey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RoomDateReservedPK)) {
			return false;
		}
		RoomDateReservedPK castOther = (RoomDateReservedPK)other;
		return 
			(this.roomRoomId == castOther.roomRoomId)
			&& this.dateReservedKey.equals(castOther.dateReservedKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roomRoomId;
		hash = hash * prime + this.dateReservedKey.hashCode();
		
		return hash;
	}
}