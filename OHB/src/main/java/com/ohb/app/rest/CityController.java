package com.ohb.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ohb.app.model.type.City;
import com.ohb.app.repo.CityRepository;
import com.ohb.app.util.OhbUtil;

@RestController
@RequestMapping(value = "/city")
public class CityController {
	@Autowired
	CityRepository repo;
	/*
	 * @Autowired public CityController(CityRepository repo) { super();
	 * this.repo = repo; }
	 */

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@RequestBody City city, Model model) throws Throwable {
		String msg = "";
		City currentCity = repo.save(city);
		if (currentCity == null) {
			msg = "Not able to add city please contact administrator";
			model.addAttribute("message", msg);
			return OhbUtil.convertToJSONWithoutNull(model);
		}
		msg = "City Added Succeffuly";
		model.addAttribute("message", msg);
		model.addAttribute("city", currentCity);
		model.addAttribute("cityList", repo.findAll());
		return OhbUtil.convertToJSONWithoutNull(currentCity);
	}

}
