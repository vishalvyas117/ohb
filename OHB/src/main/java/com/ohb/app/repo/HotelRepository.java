package com.ohb.app.repo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Hotel;
import com.ohb.app.model.type.Category;
import com.ohb.app.model.type.City;

@Repository(value = "hotelRepository")
public interface HotelRepository extends CrudRepository<Hotel, Integer>, JpaSpecificationExecutor<Hotel> {
	//@Query(nativeQuery = true,value = "select ho from Hotel as ho where ho.name in ?1")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByNameContaining(String name);

	/*@Query(value = "SELECT ho,ho.hotelid from Hotel as ho left join ho.name as ho2 group by ho.city having count(ho.hotelid) <= 3")
	List<Hotel> findtop3HotelsforeachCity();*/
	
	//@Query(nativeQuery = true, value = "select ho from Hotel ho where ho.city = :city_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByCity(City city);
	
		/*@Query(nativeQuery = true, value = "select * from Hotel as ho join room as ro on ro.hotel_id=ho.hotel_id")
		@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
		List<Hotel> findHotelswithRooms();*/

	//@Query(nativeQuery = true, value = "select ho from Hotel ho where ho.address in %?1%")
	List<Hotel> findHotelsByAddressContains(String address);

//	@Query(value = "select ho from Hotel ho where ho.rating = :rating")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByRatingLessThanEqual(int rating);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByRatingGreaterThanEqual(@Param("rating") int rating);

	@Query(value = "select ho from Hotel ho where ho.category = :category_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByCategory(@Param("category_id") Category category_id);

}
