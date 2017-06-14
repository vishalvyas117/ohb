package com.ohb.app.repo;

import java.util.Date;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Comment;
import com.ohb.app.model.Hotel;

@Repository(value="commentRepository")
public interface CommentRepository extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
	
	@Query(value = "select co from Comment ro where co.hotel =:Hotel_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Comment> findCommentsByHotelId(@Param("Hotel_id") Integer Hotel_id);
	
	@Query(value = "select co from Comment ro where co.user =:User_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Comment> findCommentsByUserId(@Param("User_id") Integer User_id);
	
	@Query(value = "select co from Comment ro where date(co.date) =:date")
	List<Comment> findCommentsByDate(@Param("date") Date date);
}
