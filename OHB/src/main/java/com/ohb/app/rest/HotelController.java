package com.ohb.app.rest;

import java.util.HashMap;
import java.util.Map;

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
import com.ohb.app.service.HotelService;
import com.ohb.app.util.OhbUtil;
import com.ohb.app.util.api.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "products")
@RestController
@RequestMapping(APIName.HOTEL)
public class HotelController extends APIUtil{
	@Autowired
	HotelService hotelService;

	@ApiOperation(value = "get list of Hotels", notes = "")
    @RequestMapping(method = RequestMethod.GET, produces = APIName.CHARSET)
    public String getAllHotels(
            @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
		Map<String, Object> result = new HashMap();
			
        Page<Hotel> products = hotelService.findAllforUser(pageNumber, pageSize);
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), products.getContent(), products.getTotalElements());
        return writeObjectToJson(statusResponse);
    }
	
	@ApiOperation(value = "get Hotel by Id", notes = "")
    @RequestMapping(path = APIName.HOTEL_BY_ID, method = RequestMethod.GET, produces = APIName.CHARSET)
    public String getProductById( @PathVariable Integer hotel_id) {
        // get product
        Hotel hotel = hotelService.hotelRepository.findOne(hotel_id);
        // get all attributes of product

        Map<String, Object> result = new HashMap();
        /*result.put("product", p);
        result.put("attributes", pad);*/
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);

        return writeObjectToJson(statusResponse);
    }

	

}
