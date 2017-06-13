package com.ohb.app.rest;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohb.app.api.response.StatusResponse;
import com.ohb.app.model.Hotel;
import com.ohb.app.repo.CategoryRepository;
import com.ohb.app.repo.CityRepository;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.repo.UserRepository;
import com.ohb.app.util.OhbUtil;
import com.ohb.app.util.api.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "products")
@RestController
@RequestMapping(APIName.HOTEL)
public class HotelController extends APIUtil{

	HotelRepository hotels;

	CategoryRepository categories;

	RoomTypeRepository roomTypes;

	RoomRepository rooms;
	CityRepository cities;

	UserRepository users;

	@Autowired
	public HotelController(HotelRepository hotels, CategoryRepository categories, RoomTypeRepository roomTypes,
			RoomRepository rooms, UserRepository users, CityRepository cities) {
		super();
		this.hotels = hotels;
		this.categories = categories;
		this.roomTypes = roomTypes;
		this.rooms = rooms;
		this.users = users;
		this.cities = cities;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(@RequestParam(required = false) HashMap<String, String> allRequestParams,
			HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String City,
			@RequestParam(required = false) String checkIn, @RequestParam(required = false) String checkOut)
			throws Throwable {
		

		return null;
		//OhbUtil.convertToJSONWithoutNull();
	}

	/*@RequestMapping(method = RequestMethod.POST)
	public String addHotel(@RequestBody Hotel hotel, Model model) throws Throwable {
		String msg = "";
		if (hotel == null) {
			msg = "Hotel Information not found";
			model.addAttribute("message", msg);
			return OhbUtil.convertToJSONWithoutNull(model);
		}
		Hotel addHotel = hotels.save(hotel);
		if (addHotel == null) {
			msg = "Sorry Unable to add Hotel";
			model.addAttribute("message", msg);
			return OhbUtil.convertToJSONWithoutNull(model);
		}
		model.addAttribute("hotel", addHotel);
		return OhbUtil.convertToJSONWithoutNull(addHotel);
	}*/
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "get product by company id", notes = "")
    @RequestMapping(method = RequestMethod.GET, produces = APIName.CHARSET)
    public String getAllProducts(
            @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {

        Page<Hotel> allhotels = (Page<Hotel>) hotels.findAll();
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), allhotels.getContent(), allhotels.getTotalElements());

        return writeObjectToJson(statusResponse);
    }	

}
