package com.ohb.app.repo;

import java.util.List;
import java.util.Set;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Hotel;
import com.ohb.app.model.type.Category;
import com.ohb.app.model.type.City;

@Repository(value = "hotelRepository")
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Integer>, JpaSpecificationExecutor<Hotel> {

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByCity(City city);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Hotel findOneByNameAndAddress(String name, String address);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByAddressContains(String address);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByNameContains(String name);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByRatingLessThanEqual(int rating);
	
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByRatingGreaterThanEqual(int rating);
	
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByCategory(Category category);
	
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByNameOrAddressContains(String name,String address);
	@Query(value="from Hotel ro where ro.name in ?1")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByNameIn(Set<String> name);
	@Query(value="from Hotel ro where ro.address in ?1")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByAddressIn(Set<String> address);
	
	@Query(value="from Hotel ro where ro.name LIKE %?1%")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByNameLike(String name);
	@Query(value="from Hotel ro where ro.address LIKE %?1%")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByAddressLike(String address);
	/*@Query(nativeQuery=true, value="from Hotel ro where ro.address LIKE %?1%")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsforHomePage(String address);*/
	
	
	
	

}
