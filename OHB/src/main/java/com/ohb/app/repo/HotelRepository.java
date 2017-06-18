package com.ohb.app.repo;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Hotel;
import com.ohb.app.model.type.Category;
import com.ohb.app.model.type.City;

@Repository(value = "hotelRepository")
public interface HotelRepository extends JpaRepository<Hotel, Integer>, JpaSpecificationExecutor<Hotel> ,PagingAndSortingRepository<Hotel, Integer>{
	//@Query(nativeQuery = true,value = "select ho from Hotel as ho where ho.name in ?1")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Page<Hotel> findHotelsByNameContaining(String name, Pageable pageable);

	@Query(nativeQuery = true,value = "SELECT * FROM Hotel AS a WHERE (SELECT COUNT(*) "
			+ "FROM Hotel AS b WHERE b.city_id = a.city_id AND b.hotel_name >= a.hotel_name) <= 3"
			+ " ORDER BY a.city_id ASC, a.hotel_name DESC")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findtop3HotelsforeachCity();
	
	//@Query(nativeQuery = true, value = "select ho from Hotel ho where ho.city = :city_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Page<Hotel> findHotelsByCity(City city, Pageable pageable);

	//@Query(nativeQuery = true, value = "select * from Hotel ho where ho.address in %?1%")
	Page<Hotel> findHotelsByAddressContains(String address, Pageable pageable);

//	@Query(value = "select ho from Hotel ho where ho.rating = :rating")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Page<Hotel> findHotelsByRatingLessThanEqual(int rating, Pageable pageable);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Page<Hotel> findHotelsByRatingGreaterThanEqual(int rating, Pageable pageable);

	@Query(value = "select ho from Hotel ho where ho.category = :category_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Page<Hotel> findHotelsByCategory(@Param("category_id") Category category_id, Pageable pageable);

}
