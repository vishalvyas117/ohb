package com.ohb.app.rest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohb.app.api.response.StatusResponse;
import com.ohb.app.model.Comment;
import com.ohb.app.model.Hotel;
import com.ohb.app.model.User;
import com.ohb.app.repo.CommentRepository;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.UserRepository;
import com.ohb.app.service.CommentService;
import com.ohb.app.util.ResponsePayLoad;
import com.ohb.app.util.api.APIName;
import com.ohb.app.util.api.APIStatus;
import com.ohb.app.util.api.APIUtil;
import com.ohb.app.util.api.DateUtil;
@RestController
@RequestMapping(APIName.REVIEWS)
public class ReviewController extends APIUtil{

	@Autowired
	HotelRepository hotels;
	
	@Autowired
	CommentRepository comments;
	
	@Autowired
	UserRepository users;
	
	@Autowired
	CommentService commentService;
	
	
	@RequestMapping(path = APIName.REVIEWS_BY_USER_ID, method = RequestMethod.POST, produces = APIName.CHARSET)
	public String reviewByUser(@PathVariable("hotel_id") Integer hotel_id,@PathVariable("user_id") String id, @RequestBody Comment comment) {
		ResponsePayLoad result=new ResponsePayLoad();
		Hotel hotel = hotels.findOne(hotel_id);
		Date date = new Date();
		DateUtil.toDbString(date);
    	comment.setDate(DateUtil.toDateString(date,TimeZone.getDefault()));
    	User user =users.findOne(id);
    	comment.setUser(user);
    	comment.setHotel(hotel);
    	Comment current=comments.save(comment);
    	current=comments.findOne(current.getComment_id());
		result.put("hotel", hotel);
		result.put("comment", current);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}
	@RequestMapping(path = APIName.REVIEWS_BY_USER_ID, method = RequestMethod.GET, produces = APIName.CHARSET)
	public String getreviewByUser(@PathVariable("hotel_id") Integer hotel_id,@PathVariable("user_id") String id) {
		ResponsePayLoad result=new ResponsePayLoad();
		List<Comment> review=this.commentService.findCommentsByUserIdagainstHotel(hotel_id, id); 
		Hotel hotel =review.get(0).getHotel();
		result.put("hotel", hotel);
		result.put("comment", review);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}
	
	@RequestMapping( method = RequestMethod.GET, produces = APIName.CHARSET)
	public String findCommentsByUserId(@PathVariable("hotel_id") Integer hotel_id) {
		ResponsePayLoad result=new ResponsePayLoad();
		Hotel hotel=this.hotels.findOne(hotel_id);
		List<Comment> review=this.commentService.findCommentsByHotel(hotel);
		result.put("hotel", hotel);
		result.put("comment", review);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}
	
	@RequestMapping(path = APIName.REVIEWS_BY_DATE, method = RequestMethod.GET, produces = APIName.CHARSET)
	public String findCommentsByDate(@PathVariable("hotel_id") Integer hotel_id,@RequestParam(value="date") String date) throws ParseException {
		ResponsePayLoad result=new ResponsePayLoad();
		/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = df.parse(date);*/
		Hotel hotel=this.hotels.findOne(hotel_id);
		List<Comment> review=this.commentService.findCommentsByDate(date);
		result.put("hotel", hotel);
		result.put("comment", review);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}
	
}
