package com.ohb.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Commentid;

	private String text;
	private Date date;
	private boolean status;

	@ManyToOne
	private Hotel hotel;

	@ManyToOne
	private User user;
	
	public Comment() {}

	public Comment(Integer id, String text, Date date, User user, boolean status, Hotel hotel) {
		this.Commentid = id;
		this.text = text;
		this.date = date;
		this.user = user;
		this.status = status;
		this.hotel = hotel;
	}

	public Integer getCommentid() {
		return Commentid;
	}

	public void setCommentid(Integer commentid) {
		Commentid = commentid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
