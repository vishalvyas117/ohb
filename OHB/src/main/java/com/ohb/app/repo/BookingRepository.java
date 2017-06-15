package com.ohb.app.repo;

import java.util.Date;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Booking;
import com.ohb.app.model.Hotel;

@Repository(value = "bookingRepository")
public interface BookingRepository extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {

	@Query(value = "select book from Booking book where book.user = :user_id order by begin_date DESC")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<Booking> findBookingsByUser(@Param("user_id") String user_id);

	@Query(value = "select book from Booking book where book.room = :room_id order by begin_date DESC")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<Booking> findBookingsByRoom(@Param("room_id") Integer room_id);

	@Query(value = "select book from Booking book where book.begin_date => date(:begindDay) and book.end_date <= date(:endDay) order by begin_date DESC")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<Booking> findBookingsByreservedDays(@Param("begindDay") String begindDay, @Param("endDay") String endDay);

	@Query(value = "select book from Booking book where book.status = :status order by begin_date DESC")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<Booking> findBookingsByStatus(@Param("status") boolean status);

}
