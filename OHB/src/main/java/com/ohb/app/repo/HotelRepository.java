package com.ohb.app.repo;

import java.util.List;
import java.util.Set;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Hotel;

@Repository(value = "hotelRepository")
public interface HotelRepository extends JpaRepository<Hotel, Integer>, JpaSpecificationExecutor<Hotel> {
	@Query(nativeQuery = true,value = "select ho from Hotel ho where ho.name like :name")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByName(@Param("name")String name);

	/*@Query(value = "SELECT ho,ho.hotelid from Hotel as ho left join ho.name as ho2 group by ho.city having count(ho.hotelid) <= 3")
	List<Hotel> findtop3HotelsforeachCity();*/
	
	@Query(nativeQuery = true, value = "select ho from Hotel ho where ho.city = :city_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByCity(@Param("city_id") Integer city_id);

	@Query(value = "select ho from Hotel ho where ho.address in (:address)")
	List<Hotel> findHotelsByAddress(@Param("address") Set<String> address);

	@Query(value = "select ho from Hotel ho where ho.rating = :rating")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByRating(@Param("rating") int rating);

	@Query(value = "select ho from Hotel ho where ho.rating <= :rating")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByUptoRating(@Param("rating") int rating);

	@Query(value = "select ho from Hotel ho where ho.category = :category_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByCategory(@Param("category_id") Integer category_id);

}
