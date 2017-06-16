package com.ohb.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohb.app.model.Comment;
import com.ohb.app.model.Hotel;
import com.ohb.app.repo.CommentRepository;
import com.ohb.app.repo.HotelRepository;
import com.ohb.app.repo.UserRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	UserRepository userRepository;

	public Comment createCommentbyHotel(Integer hotelId, Comment Comment) {
		Comment dto = new Comment();
		Comment.setHotel(hotelRepository.findOne(hotelId));
		Comment.setStatus(true);
		Comment comment = this.commentRepository.save(Comment);
		dto.setHotel(comment.getHotel());
		dto.setText(comment.getText());
		dto.setUser(comment.getUser());
		dto.setDate(comment.getDate());
		return dto;
	}

	public Comment createCommentbyUserAgainstHotel(Integer hotelId, String userId, Comment Comment) {
		Comment dto = new Comment();
		Comment.setHotel(hotelRepository.findOne(hotelId));
		Comment.setUser(userRepository.findOne(userId));
		Comment.setStatus(true);
		Comment comment = this.commentRepository.save(Comment);
		dto.setHotel(comment.getHotel());
		dto.setText(comment.getText());
		dto.setUser(comment.getUser());
		dto.setDate(comment.getDate());
		return dto;
	}

	public List<Comment> findCommentsByHotelId(Integer hotelId) {
		Hotel hotel=hotelRepository.findOne(hotelId);
		List<Comment> page = this.commentRepository.findCommentsByHotel(hotel);
		List<Comment> commentList = new ArrayList<Comment>();
		for (Comment comment : page) {
			Comment dto = new Comment();
			dto.setHotel(comment.getHotel());
			dto.setText(comment.getText());
			dto.setUser(comment.getUser());
			dto.setDate(comment.getDate());
			commentList.add(dto);
		}
		return commentList;
	}

	public List<Comment> findCommentsByUserId(String UserId) {
		List<Comment> page = this.commentRepository.findCommentsByUser(UserId);
		List<Comment> commentList = new ArrayList<Comment>();
		for (Comment comment : page) {
			Comment dto = new Comment();
			dto.setHotel(comment.getHotel());
			dto.setText(comment.getText());
			dto.setUser(comment.getUser());
			dto.setDate(comment.getDate());
			commentList.add(dto);
		}
		return commentList;
	}

	public List<Comment> findCommentsByUserIdagainstHotel(Integer hotelId, String UserId) {
		List<Comment> page = this.commentRepository.findCommentsByUserAndHotel(hotelId, UserId);
		List<Comment> commentList = new ArrayList<Comment>();
		for (Comment comment : page) {
			Comment dto = new Comment();
			dto.setHotel(comment.getHotel());
			dto.setText(comment.getText());
			dto.setUser(comment.getUser());
			dto.setDate(comment.getDate());
			commentList.add(dto);
		}
		return commentList;
	}

	public List<Comment> findCommentsByDate(Date date) {
		List<Comment> page = this.commentRepository.findCommentsByDate(date);
		List<Comment> commentList = new ArrayList<Comment>();
		for (Comment comment : page) {
			Comment dto = new Comment();
			dto.setHotel(comment.getHotel());
			dto.setText(comment.getText());
			dto.setUser(comment.getUser());
			dto.setDate(comment.getDate());
			commentList.add(dto);
		}
		return commentList;
	}
}
