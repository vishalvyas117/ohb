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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "REVIEW")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Comment {

	@Id
	@Column(name = "COMMENT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Comment_id;
	
	@Length(min = 13, max = 10000)
	@Column(name = "COMMENT_DESCRIPTION")
	private String text;
	
	@Column(name = "COMMENT_DATE")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
	private String date;
	
	@Column(name = "COMMENT_STATUS")
	private boolean status;
	
	
	
	@JsonIgnore
	@JsonSerialize
	@JsonDeserialize	
	@ManyToOne(optional=false,cascade=CascadeType.MERGE )
    @JoinColumn(name = "hotel_id", referencedColumnName="hotel_id",nullable=false,updatable=false)
	private Hotel hotel;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = true)
	private User user;
	
	public Comment() {}

	public Comment(Integer id, String text, String date, User user, boolean status, Hotel hotel) {
		this.Comment_id = id;
		this.text = text;
		this.date = date;
		this.user = user;
		this.status = status;
		this.hotel = hotel;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

	public Integer getComment_id() {
		return Comment_id;
	}

	public void setComment_id(Integer comment_id) {
		Comment_id = comment_id;
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
