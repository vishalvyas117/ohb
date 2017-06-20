package com.ohb.app.repo;

import java.util.Date;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Comment;
import com.ohb.app.model.Hotel;

@Repository(value="commentRepository")
public interface CommentRepository extends CrudRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
	
	//@Query(value = "select co from Comment ro where co.hotel =:Hotel_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Comment> findCommentsByHotel(Hotel hotel_id);
	
	//@Query(value = "select co from Comment ro where co.user =:User_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Comment> findCommentsByUser(String User_id);
	
	//@Query(value = "select co from Comment ro where co.hotel=:Hotel_id and co.user =:User_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Comment> findCommentsByUserAndHotel(Integer Hotel_id,String User_id);
	
	//@Query(value = "select co from Comment ro where date(co.date) =:date")
	public List<Comment> findCommentsByDate(@Param("date") Date date);
}
