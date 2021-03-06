package com.ohb.app.repo;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Booking;

@Repository(value = "bookingRepository")
public interface BookingRepository extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {
	
	
	
	//@Query(value = "select book from Booking book where book.user = :user_id order by begin_date DESC")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<Booking> findBookingsByUser(String user_id);

	//@Query(value = "select book from Booking book where book.room = :room_id order by begin_date DESC")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<Booking> findBookingsByRoom(Integer room_id);

	/*//@Query(value = "select book from Booking book where  book.begin_date between  order by begin_date DESC")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<Booking> findBookingsByBegin_dateAndEnd_DateIsBetween(String begindDay, String endDay);*/

//	@Query(value = "select book from Booking book where book.status = :status order by begin_date DESC")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<Booking> findBookingsByState(boolean status);

}
