package com.ohb.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ohb.app.model.Room;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.service.BookingService;
import com.ohb.app.util.OhbUtil;

@RestController
@RequestMapping(value="hotel")
public class RoomController {
	 
	
	@Autowired
    RoomTypeRepository roomTypes;
    
    @Autowired
    RoomRepository rooms;
    @Autowired
    HotelRepository hotels;
    @Autowired
    BookingService bookingServise;
    
	@RequestMapping(value="{id}/rooms/new", method=RequestMethod.GET)
    public String newRoom(@PathVariable("id") Integer id, Model model) throws Throwable {
    	Room r = new Room();
    	model.addAttribute("hotel", hotels.findOne(id));
    	
    	model.addAttribute("room", r);
    	model.addAttribute("roomTypes", roomTypes.findAll());
    	return OhbUtil.convertToJSONWithoutNull(model);
    }

}
