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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "IMAGE")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Image {

	@Column(name = "IMAGE_PATH")
	@NotNull
	private String path;
	
	@JoinColumn(name = "HOTEL_ID")
	private Integer hotel_id;

	@Id
	@Column(name = "IMAGE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long imageId;

	@Column(name = "INSERTION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date insertion_date;

	public Image() {
		super();
	}

	public Image(String path, Integer hotel, Date insertion_date) {
		super();
		this.path = path;
		this.hotel_id = hotel;
		this.insertion_date = insertion_date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public Date getInsertion_date() {
		return insertion_date;
	}

	public void setInsertion_date(Date insertion_date) {
		this.insertion_date = insertion_date;
	}
	
}
