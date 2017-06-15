package com.ohb.app.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohb.app.model.Booking;
import com.ohb.app.repo.BookingRepository;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.repo.UserRepository;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoomTypeRepository roomTypeRepository;

	public Booking Save(Booking booking) {
		Booking dto = new Booking();
		dto = bookingRepository.save(booking);
		//return Error Message if dto return null;
		//check duplicate booking of same hotel via same person
		return dto;
	}
	public Booking getBookingByID(Integer bookingid){
		if(bookingid==null){
			bookingid=0;
		}
		Booking booking=this.bookingRepository.findOne(bookingid);
		return booking;
	}
	
	public List<Booking>findBookingsByUser(String user_id){
		if(user_id==null){
			user_id="";
		}
		List<Booking> page = this.bookingRepository.findBookingsByUser(user_id);
		List<Booking> bookingList = fillList(page);
		return bookingList;
	}
	
	public List<Booking>findBookingsByRoom(Integer room_id){
		if(room_id==null){
			room_id= 0;
		}
		List<Booking> page = this.bookingRepository.findBookingsByRoom(room_id);
		List<Booking> bookingList = fillList(page);
		return bookingList;
	}
	public List<Booking>findBookingsByreservedDays(String begindDay,String end_date){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");//at create time date should be String format and MM/dd/yyyy formate
		LocalDate localDate = LocalDate.now();
		if(begindDay==null){
			begindDay= dtf.format(localDate);
		}
		if(end_date==null){
			end_date= dtf.format(localDate);
		}
		List<Booking> page = this.bookingRepository.findBookingsByreservedDays(begindDay,end_date);
		List<Booking> bookingList = fillList(page);
		return bookingList;
	}
	public List<Booking>findBookingsByStatus(boolean status){
		if(!status){
			status= false;
		}
		List<Booking> page = this.bookingRepository.findBookingsByStatus(status);
		List<Booking> bookingList = fillList(page);
		return bookingList;
	}

	public List<Booking> fillList(List<Booking> page) {
		List<Booking> hotellist = new ArrayList<Booking>();
		for (Booking booking : page) {
			Booking dto = new Booking();
			dto.setBookingid(booking.getBookingid());
			dto.setBegin_date(booking.getBegin_date());
			dto.setEnd_date(booking.getEnd_date());
			dto.setRoom(booking.getRoom());
			dto.setState(booking.isState());
			dto.setUser(booking.getUser());
			hotellist.add(dto);
		}
		return hotellist;
	}

}
