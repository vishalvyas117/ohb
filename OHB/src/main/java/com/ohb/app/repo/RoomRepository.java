package com.ohb.app.repo;

import java.util.Date;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Room;

@Repository(value = "roomRepository")
public interface RoomRepository extends JpaRepository<Room, Integer>,
JpaSpecificationExecutor<Room> {
	
	@Query(value="select ro from Room ro where ro.floor = :floor")
	public Page<Room> getRoomsbyFloor(@Param("floor") int floor,  Pageable pageable);
	
	@Query(value="select ro from Room ro where ro.room_number = :roomNumber")
	public Page<Room> getRoomsbyRoomNumber(@Param("roomNumber") String roomNumber,  Pageable pageable);
	
	@Query(value="select ro from Room ro where ro.HOTEL_ID = :hotelId")
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	public List<Room> getRoomsbyhotel(@Param("hotelId") Integer hotelId);
	
	@Query(value="select ro from Room ro where date(ro.days_reserved) >= :checkIn and date(so.days_reserved) <= :checkout")
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	public List<Room> getRoomsbydaysReserved(@Param("checkIn") Date checkIn,@Param("checkout") Date checkout);
	
	@Query(value="select ro from Room ro where so.price <= :price")
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	public List<Room> getRoomsbyprice(@Param("price") double price);
	
	@Query(value="SELECT * from Product_sales where price BETWEEN (:minprice, :maxprice)")
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	public List<Room> getRoomsbypriceRange(@Param("minprice") double minprice,@Param("maxprice") double maxprice);
}
