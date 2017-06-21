package com.ohb.app.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.ohb.app.model.Booking;
import com.ohb.app.model.Comment;
import com.ohb.app.model.Hotel;
import com.ohb.app.model.Room;
import com.ohb.app.model.type.City;
import com.ohb.app.repo.CategoryRepository;
import com.ohb.app.repo.CityRepository;
import com.ohb.app.repo.CommentRepository;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.RoomRepository;
import com.ohb.app.repo.RoomTypeRepository;
import com.ohb.app.repo.UserRepository;
import com.ohb.app.service.HotelService;
import com.ohb.app.service.RoomService;
import com.ohb.app.util.OhbUtil;
import com.ohb.app.util.api.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "hotel")
@RestController
@RequestMapping(APIName.HOTEL)
public class HotelController extends APIUtil{
	@Autowired
	HotelService hotelService;
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	RoomRepository rooms;
	
	@Autowired
	RoomService roomsType;
	
	@Autowired
	CategoryRepository categories;
	
	@Autowired
	CommentRepository comments;
	
	@Autowired
	UserRepository users;
	@Autowired
	CityRepository cities;

	@ApiOperation(value = "get list of Hotels", notes = "")
    @RequestMapping(method = RequestMethod.GET, produces = APIName.CHARSET)
    public String getAllHotels(
            @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
		Map<String, Object> result = new HashMap();
			
        List<Hotel> products = hotelService.findAllforUser(pageNumber, pageSize);
        /*products.forEach(hotel->
        Map::<Integer, Comment> comments=new HashMap<>();
        hotel.setComments(comments.findCommentsByHotel(hotel))
		);*/
        List<Comment> comment=new ArrayList<Comment>();
        for(Hotel hotel:products){
        comment=this.comments.findCommentsByHotel(hotel);
        System.out.println("comment  "+comment.toString());
		}
        
        /*for(Hotel hotel:products){
        	int index=0;
        	Map<Integer, Comment> newcomments=new HashMap<>();
        	List<Comment> comment=this.comments.findCommentsByHotel(hotel);
        	for(Comment com:comment){
        		newcomments.put(com.getCommentid(), com);
        	}
        	hotel.setComment(newcomments);
        	
        	products.set(index, hotel);
        }*/
        result.put("hotels", products);
        result.put("comments", comment);
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
        return writeObjectToJson(statusResponse);
    }
	
	@ApiOperation(value = "get Hotel by Id", notes = "")
    @RequestMapping(path = APIName.HOTEL_BY_ID, method = RequestMethod.GET, produces = APIName.CHARSET)
    public String getHotelById(@PathVariable(value="hotelid") int hotel_id) {
        // get product
        Hotel hotel = hotelRepository.findOne(hotel_id);
        List<Comment> hotel_comments = comments.findCommentsByHotel(hotel);

        Map<String, Object> result = new HashMap();
        result.put("Hotel", hotel);
        result.put("Booking", new Booking());
        result.put("comments",hotel_comments);
        result.put("Category", categories.findAll());
        result.put("reply", new Comment());
        result.put("users", users.findAll());
        result.put("roomTypes", roomsType.getAllRoomType());
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);

        return writeObjectToJson(statusResponse);
    }
	
	
	@ApiOperation(value = "get Hotel by Id", notes = "")
    @RequestMapping(path = APIName.HOTEL_BY_NAME, method = RequestMethod.GET, produces = APIName.CHARSET)
    public String getProductByName(@PathVariable(value="hotelname") String hotelname) {
		Map<String, Object> result = new HashMap();
        List<Hotel> hotels = hotelService.findHotelsByNameIn(hotelname);
        for(Hotel hotel:hotels){
        List<Comment> hotel_comments = comments.findCommentsByHotel(hotel);
        result.put("comments",hotel_comments);
        }
        result.put("Hotels", hotels);
        result.put("Booking", new Booking());
        result.put("Category", categories.findAll());
        result.put("reply", new Comment());
        result.put("users", users.findAll());
        result.put("roomTypes", roomsType.getAllRoomType());
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
        return writeObjectToJson(statusResponse);
    }
	
	@ApiOperation(value = "get Hotel by Id", notes = "")
    @RequestMapping(path = APIName.HOTEL_BY_CITY, method = RequestMethod.GET, produces = APIName.CHARSET)
    public String getProductByCityName(@PathVariable(value="cityName") String cityName) {
		Map<String, Object> result = new HashMap();
		
		City city=cities.findByName(cityName);
        List<Hotel> hotels = hotelService.findHotelsByCity(city);
        for(Hotel hotel:hotels){
        List<Comment> hotel_comments = comments.findCommentsByHotel(hotel);
        result.put("comments",hotel_comments);
        }
        result.put("Hotels", hotels);
        result.put("Booking", new Booking());
        result.put("Category", categories.findAll());
        result.put("reply", new Comment());
        result.put("users", users.findAll());
        result.put("roomTypes", roomsType.getAllRoomType());
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
        return writeObjectToJson(statusResponse);
    }
	
	@ApiOperation(value = "get Hotel by Id", notes = "")
    @RequestMapping(path = APIName.HOTEL_BY_ADDRESS, method = RequestMethod.GET, produces = APIName.CHARSET)
    public String getProductByaddressName(@PathVariable(value="address") String address) {
		Map<String, Object> result = new HashMap();
        List<Hotel> hotels = hotelService.findHotelsByAddress(address.trim());
        for(Hotel hotel:hotels){
        List<Comment> hotel_comments = comments.findCommentsByHotel(hotel);
        result.put("comments",hotel_comments);
        }
        result.put("Hotels", hotels);
        result.put("Booking", new Booking());
        result.put("Category", categories.findAll());
        result.put("reply", new Comment());
        result.put("users", users.findAll());
        result.put("roomTypes", roomsType.getAllRoomType());
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
        return writeObjectToJson(statusResponse);
    }
	
	@ApiOperation(value = "get Hotel by Id", notes = "")
    @RequestMapping(path = APIName.HOTEL_BY_RATING, method = RequestMethod.GET, produces = APIName.CHARSET)
    public String getHotelstByRating(@PathVariable(value="rating") int rating) {
		Map<String, Object> result = new HashMap();
        List<Hotel> hotels = hotelService.findHotelsByRating(rating);
        for(Hotel hotel:hotels){
        List<Comment> hotel_comments = comments.findCommentsByHotel(hotel);
        result.put("comments",hotel_comments);
        }
        result.put("Hotels", hotels);
        result.put("Booking", new Booking());
        result.put("Category", categories.findAll());
        result.put("reply", new Comment());
        result.put("users", users.findAll());
        result.put("roomTypes", roomsType.getAllRoomType());
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
        return writeObjectToJson(statusResponse);
    }
	@ApiOperation(value = "get Hotel by Id", notes = "")
    @RequestMapping(path = APIName.HOTEL_BY_RATING_GREATOR, method = RequestMethod.GET, produces = APIName.CHARSET)
    public String getHotelstByRatingGreator(@PathVariable(value="rating") int rating) {
		Map<String, Object> result = new HashMap();
        List<Hotel> hotels = hotelService.findHotelsByUptoRating(rating);
        for(Hotel hotel:hotels){
        List<Comment> hotel_comments = comments.findCommentsByHotel(hotel);
        result.put("comments",hotel_comments);
        }
        result.put("Hotels", hotels);
        result.put("Booking", new Booking());
        result.put("Category", categories.findAll());
        result.put("reply", new Comment());
        result.put("users", users.findAll());
        result.put("roomTypes", roomsType.getAllRoomType());
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
        return writeObjectToJson(statusResponse);
    }
	
	
	@ApiOperation(value = "get Hotel by Id", notes = "")
    @RequestMapping(path = APIName.HOTEL_REGISTER, method = RequestMethod.POST, produces = APIName.CHARSET)
    public String saveHotel(@RequestBody Hotel hotel) {
        Hotel currenthotel = hotelService.createHotel(hotel);

        Map<String, Object> result = new HashMap();
        result.put("Hotels", currenthotel);
        result.put("Rooms", new Room());
        result.put("Category", categories.findAll());
        result.put("RoomsType", roomsType.getAllRoomType());
        
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);

        return writeObjectToJson(statusResponse);
    }

	

}
