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

@Repository(value = "commentRepository")
public interface CommentRepository extends CrudRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Comment> findByHotel(Hotel hotel);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Comment> findCommentsByUser(User user);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Comment> findCommentsByUserAndHotel(User user, Hotel hotel);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Comment> findCommentsByDate(@Param("date") String date);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<Comment> findCommentsByHotelAndUser(Hotel hotel, User user);

}
