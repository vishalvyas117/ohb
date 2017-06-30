package com.ohb.app.repo;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.type.City;

@Repository(value="cityRepository")
public interface CityRepository extends CrudRepository<City, Integer> {
	
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	City findByName(String name);
	
	
	@Query(nativeQuery=true,value="SELECT * FROM city ct LIMIT 10 ")
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	List<City>findCityWithLimit();
}
