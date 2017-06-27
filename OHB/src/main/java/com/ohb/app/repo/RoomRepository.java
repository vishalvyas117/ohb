package com.ohb.app.repo;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Hotel;
import com.ohb.app.model.Room;

@Repository(value = "roomRepository")
public interface RoomRepository extends CrudRepository<Room, Integer>, JpaSpecificationExecutor<Room> {

	public List<Room> findRoomsByFloor(int floor);

	public List<Room> findRoomsByRoom_number(String room_number);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Room> findRoomsByPriceBetween(double minprice, double maxprice);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Room> findRoomsByDateReservedIsBetween(String checkIn, String checkout);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Room> findRoomsByPriceLessThanEqual(double price);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Room> findRoomsByHotel(Hotel hotel);
}
