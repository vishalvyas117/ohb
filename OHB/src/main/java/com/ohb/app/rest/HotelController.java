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
import com.ohb.app.util.ResponsePayLoad;
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
		ResponsePayLoad result=new ResponsePayLoad();
			
        Iterable<Hotel> products =  this.hotelRepository.findAll();
        List<Comment> comment=new ArrayList<Comment>();
        Map<Integer, List<Comment>> comments=new HashMap<>();
        List<Room> room=new ArrayList<Room>();
        Map<Integer, List<Room>> rooms=new HashMap<>();
        for(Hotel hotel:products){
        comment=this.comments.findByHotel(hotel);
        comments.put(hotel.getHotel_id(), comment);
        room=this.rooms.findByHotel(hotel);
        rooms.put(hotel.getHotel_id(), room);
        System.out.println("comment  "+comments);
		}
        result.put("hotels", products);
        result.put("comments", comments);
        result.put("rooms", rooms);
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
        return writeObjectToJson(statusResponse);
    }
	
	@ApiOperation(value = "get Hotel by Id", notes = "")
    @RequestMapping(path = APIName.HOTEL_BY_ID, method = RequestMethod.GET, produces = APIName.CHARSET)
    public String getHotelById(@PathVariable(value="hotelid") int hotel_id) {
        Hotel hotel = hotelRepository.findOne(hotel_id);
        List<Comment> hotel_comments = comments.findByHotel(hotel);
        Map<Integer,Comment> com=new HashMap<Integer,Comment>();
        List<Room> hotel_rooms = rooms.findByHotel(hotel);
        Map<Integer,Room> rom=new HashMap<Integer,Room>();
        hotel_comments.forEach(co->
        com.put(co.getComment_id(), co)
		);
        hotel_rooms.forEach(ro->
        rom.put(ro.getRoom_id(), ro)
        
		);
        hotel.setComments(com);
        ResponsePayLoad result=new ResponsePayLoad();
        result.put("Hotel", hotel);
        result.put("Booking", new Booking());
        result.put("comments",hotel_comments);
        result.put("Category", categories.findAll());
        result.put("reply", new Comment());
        result.put("users", users.findAll());
        result.put("roomTypes", roomsType.getAllRoomType());
        result.put("rooms", rom);
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);

        return writeObjectToJson(statusResponse);
    }
	
	
	@ApiOperation(value = "get Hotel by Id", notes = "")
    @RequestMapping(path = APIName.HOTEL_BY_NAME, method = RequestMethod.GET, produces = APIName.CHARSET)
    public String getProductByName(@PathVariable(value="hotelname") String hotelname) {
		ResponsePayLoad result=new ResponsePayLoad();
        List<Hotel> hotels = hotelService.findHotelsByNameIn(hotelname);
        for(Hotel hotel:hotels){
       /* List<Comment> hotel_comments = comments.findCommentsByHotel(hotel.getHotel_id());
        result.put("comments",hotel_comments);*/
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
		ResponsePayLoad result=new ResponsePayLoad();
		
		City city=cities.findByName(cityName);
        List<Hotel> hotels = hotelService.findHotelsByCity(city);
        for(Hotel hotel:hotels){
        /*List<Comment> hotel_comments = comments.findCommentsByHotel(hotel.getHotel_id());
        result.put("comments",hotel_comments);*/
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
		ResponsePayLoad result=new ResponsePayLoad();
        List<Hotel> hotels = hotelService.findHotelsByAddress(address.trim());
        for(Hotel hotel:hotels){
        /*List<Comment> hotel_comments = comments.findCommentsByHotel(hotel.getHotel_id());
        result.put("comments",hotel_comments);*/
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
		ResponsePayLoad result=new ResponsePayLoad();
        List<Hotel> hotels = hotelService.findHotelsByRating(rating);
        for(Hotel hotel:hotels){
       /* List<Comment> hotel_comments = comments.findCommentsByHotel(hotel.getHotel_id());
        result.put("comments",hotel_comments);*/
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
		ResponsePayLoad result=new ResponsePayLoad();
        List<Hotel> hotels = hotelService.findHotelsByUptoRating(rating);
        for(Hotel hotel:hotels){
        /*List<Comment> hotel_comments = comments.findCommentsByHotel(hotel.getHotel_id());
        result.put("comments",hotel_comments);*/
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
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "get Hotel by Id", notes = "")
    @RequestMapping(path = APIName.HOTEL_REGISTER, method = RequestMethod.POST, produces = APIName.CHARSET)
    public String saveHotel(@RequestBody Hotel hotel) {
		List<Hotel> excisted=this.hotelService.checkExsitingHotel(hotel.getName().toString(),hotel.getAddress().toString());
		ResponsePayLoad result=new ResponsePayLoad();
		if(excisted.get(0)!=null){
			result.put("error", excisted.get(0).getName()+" already excited");
			statusResponse = new StatusResponse(APIStatus.Hotel_ALREADY_EXIST.getCode(), result);
		}else{
        Hotel currenthotel = hotelService.createHotel(hotel);

        result.put("Hotels", currenthotel);
        result.put("Rooms", new Room());
        result.put("Category", categories.findAll());
        result.put("RoomsType", roomsType.getAllRoomType());
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		}
        

        return writeObjectToJson(statusResponse);
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "get Hotel by Id", notes = "")
    @RequestMapping(path = APIName.HOTEL_REGISTER, method = RequestMethod.GET, produces = APIName.CHARSET)
    public String saveHotelGET() {
        ResponsePayLoad result=new ResponsePayLoad();
        result.put("Hotels", new Hotel());
        result.put("Category", categories.findAll());
        result.put("City", cities.findAll());
        statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);

        return writeObjectToJson(statusResponse);
    }
}
