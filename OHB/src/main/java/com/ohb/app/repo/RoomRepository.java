package com.ohb.app.repo;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Hotel;
import com.ohb.app.model.Room;

@Repository(value = "roomRepository")
public interface RoomRepository extends CrudRepository<Room, Integer>, JpaSpecificationExecutor<Room> {

	@Query(value="select ro from Room ro where ro.floor = ?1")
	public List<Room> findRoomsByFloor(int floor);
	
	@Query(value="select ro from Room ro where ro.room_number = :roomNumber")
	public List<Room> findRoomsByRoom_Number(@Param("roomNumber") String roomNumber);
	
	//@Query(value="from Room ro where ro.hotel_id = :hotel_id")
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	public List<Room> findByHotel(Hotel hotel);
	
	//@Query(value="select ro from Room ro where date(ro.days_reserved) >= :checkIn and date(so.days_reserved) <= :checkout")
		@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
		public List<Room> findRoomsByDateReservedIsBetween(String checkIn,String checkout);
	
	//@Query(value="select ro from Room ro where ro.price <= :price")
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	public List<Room> findRoomByPriceLessThanEqual(@Param("price") double price);
	
	//@Query(value="select ro from Room ro where ro.price between 1? and ?2")
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	public List<Room> findRoomByPriceBetween(double minprice,double maxprice);
	
	  @Query(nativeQuery=true, value="SELECT * FROM room ro LEFT JOIN room_type rt ON rt.room_type_id=ro.room_type_id WHERE occupancy > ?1 ")
		@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
		public List<Room> getRoomsByGuestNumber(int guest);
}
