package com.ohb.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Booking;

@Repository(value = "bookingRepository")
public interface BookingRepository extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking>{

}
