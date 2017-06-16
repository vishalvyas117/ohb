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

import com.ohb.app.model.Hotel;
import com.ohb.app.model.Room;

@Repository(value = "roomRepository")
public interface RoomRepository extends JpaRepository<Room, Integer>,
JpaSpecificationExecutor<Room> {
	
	@Query(value="select ro from Room ro where ro.floor = :floor")
	public Page<Room> getRoomsbyFloor(@Param("floor") int floor,  Pageable pageable);
	
	@Query(value="select ro from Room ro where ro.room_number = :roomNumber")
	public Page<Room> getRoomsbyRoomNumber(@Param("roomNumber") String roomNumber,  Pageable pageable);
	
	//@Query(value="from Room ro where ro.hotel_id = :hotel_id")
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	public List<Room> findByHotel(Integer hotel_id);
	
	//@Query(value="select ro from Room ro where date(ro.days_reserved) >= :checkIn and date(so.days_reserved) <= :checkout")
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	public List<Room> findRoomByDateReservedIsBetween(Date checkIn,Date checkout);
	
	//@Query(value="select ro from Room ro where ro.price <= :price")
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	public List<Room> findRoomByPriceLessThanEqual(@Param("price") double price);
	
	//@Query(value="select ro from Room ro where ro.price between 1? and ?2")
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	public List<Room> findRoomByPriceBetween(double minprice,double maxprice);
}
