package com.ohb.app.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "REVIEW")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Comment {

	@Id
	@Column(name = "COMMENT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Comment_id;
	
	@Column(name = "COMMENT_DESCRIPTION")
	private String text;
	
	@Column(name = "COMMENT_DATE")
	private Date date;
	
	@Column(name = "COMMENT_STATUS")
	private boolean status;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "HOTEL_ID", referencedColumnName = "hotel_id", nullable = true)
	private Hotel hotel;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "USER_ID", referencedColumnName = "user_id", nullable = true)
	private User user;
	
	public Comment() {}

	public Comment(Integer id, String text, Date date, User user, boolean status, Hotel hotel) {
		this.Comment_id = id;
		this.text = text;
		this.date = date;
		this.user = user;
		this.status = status;
		this.hotel = hotel;
	}

	public Integer getCommentid() {
		return Comment_id;
	}

	public void setCommentid(Integer commentid) {
		Comment_id = commentid;
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
