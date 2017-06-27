package com.ohb.app.repo;

import java.util.Date;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.Comment;
import com.ohb.app.model.Hotel;
import com.ohb.app.model.User;

@Repository(value="commentRepository")
public interface CommentRepository extends CrudRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
	
	/*@Query(nativeQuery=true,value = "select co from Comment ro where co.hotel_id =:Hotel_id")*/
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Comment> findByHotel(Hotel hotel);
	
	//@Query(value = "select co from Comment ro where co.user =:User_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Comment> findCommentsByUser(User user);
	
	//@Query(value = "select co from Comment ro where co.hotel=:Hotel_id and co.user =:User_id")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Comment> findCommentsByUserAndHotel(User user,Hotel hotel);
	
	//@Query(value = "select co from Comment ro where date(co.date) =:date")
	public List<Comment> findCommentsByDate(@Param("date") Date date);
	
	public List<Comment> findCommentsByHotelAndUser(Hotel hotel,User user );
	
}
