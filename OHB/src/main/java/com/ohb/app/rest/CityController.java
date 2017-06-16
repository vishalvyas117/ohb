package com.ohb.app.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ohb.app.api.response.StatusResponse;
import com.ohb.app.model.type.City;
import com.ohb.app.repo.CityRepository;
import com.ohb.app.util.OhbUtil;
import com.ohb.app.util.api.APIStatus;
import com.ohb.app.util.api.APIUtil;

@RestController
@RequestMapping(value = "/city")
public class CityController extends APIUtil{
	@Autowired
	CityRepository repo;
	/*
	 * @Autowired public CityController(CityRepository repo) { super();
	 * this.repo = repo; }
	 */

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCity(@RequestBody City city) throws Throwable {
		 Map<String, Object> result = new HashMap();
		String msg = "";
		City currentCity = repo.save(city);
		if (currentCity == null) {
			msg="Something went wrong please contact to adminstrator";
			result.put("error", msg);
			statusResponse = new StatusResponse(APIStatus.ERR_INVALID_DATA.getCode(), result);
			return writeObjectToJson(statusResponse);
		}
		msg = currentCity.getName()+" added succeffuly";
		result.put("message", msg);
		result.put("Added City",currentCity);
		result.put("All Cities", repo.findAll());
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), result);
		return writeObjectToJson(statusResponse);
	}

}
