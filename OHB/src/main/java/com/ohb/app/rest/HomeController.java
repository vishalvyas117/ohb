package com.ohb.app.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ohb.app.model.Booking;
import com.ohb.app.model.Comment;
import com.ohb.app.model.Hotel;
import com.ohb.app.model.User;
import com.ohb.app.util.OhbUtil;

@RestController
public class HomeController {
 
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String homePage(Model model) throws Throwable {
		model.addAttribute("user", new User());
		model.addAttribute("hotel", new Hotel());
		model.addAttribute("booking", new Booking());
		model.addAttribute("Comments", new Comment());
		return OhbUtil.convertToJSONWithoutNull(model);
	}
}
