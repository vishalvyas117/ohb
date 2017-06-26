package com.ohb.app.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "IMAGE")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Image implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6205736393038149044L;


	@Column(name = "IMAGE_PATH")
	@NotNull
	private String path;
	
	
	@JsonIgnore
	@JsonSerialize
	@JsonDeserialize	
	@ManyToOne(optional=false,cascade=CascadeType.MERGE )
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

	@Id
	@Column(name = "IMAGE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long image_id;

	@Column(name = "INSERTION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date insertion_date;

	public Image() {
		super();
	}

	public Image(String path, Hotel hotel, Date insertion_date) {
		super();
		this.path = path;
		this.hotel = hotel;
		this.insertion_date = insertion_date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public long getImage_id() {
		return image_id;
	}

	public void setImage_id(long image_id) {
		this.image_id = image_id;
	}

	public Date getInsertion_date() {
		return insertion_date;
	}

	public void setInsertion_date(Date insertion_date) {
		this.insertion_date = insertion_date;
	}
	
}
