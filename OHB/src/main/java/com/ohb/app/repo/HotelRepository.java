package com.ohb.app.repo;

import java.util.List;
import java.util.Set;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Hotel;

@Repository(value = "hotelRepository")
public interface HotelRepository extends JpaRepository<Hotel, Integer>, JpaSpecificationExecutor<Hotel> {
	@Query(value = "select ho from Hotel ro where ro.floor like ?1")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByName(String name);

	@Query(value = "SELECT ho.* FROM Hotel AS a LEFT JOIN Hotel AS ho2 ON a.name = a2.name AND a.rating <= a2.rating GROUP BY a.city HAVING COUNT(*) <= 3;")
	Page<Hotel> findtop3HotelsforeachCity(Pageable pageable);
	
	@Query(value = "select ho from Hotel ro where ro.city = :city_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByCity(@Param("city_id") Integer city_id);

	@Query(value = "select ho from Hotel ro where ro.address in (:address)")
	List<Hotel> findHotelsByAddress(@Param("address") Set<String> address);

	@Query(value = "select ho from Hotel ro where ro.rating = :rating")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByRating(@Param("rating") int rating);

	@Query(value = "select ho from Hotel ro where ro.rating <= :rating")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByUptoRating(@Param("rating") int rating);

	@Query(value = "select ho from Hotel ro where ro.category = :category_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Hotel> findHotelsByCategory(@Param("category_id") Integer category_id);

}
