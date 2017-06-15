package com.ohb.app.repo;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.ohb.app.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>, JpaSpecificationExecutor<Image> {

	@Query(value = "select img from Image img where img.hotel = :hotel_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Image> findImagesByHotelId(@Param("hotel_id") Integer hotel_id);
}
