package com.ohb.app.model.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hotel_category")
public class Category {

	@Id
	@Column(name="CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer category_id;
	
	@Column(name="CATEGORY_NAME")
	private String name;

	public Category() {
		super();
	}

	public Category(Integer categoryid, String name) {
		super();
		this.category_id = categoryid;
		this.name = name;
	}

	public Integer getCategoryid() {
		return category_id;
	}

	public void setCategoryid(Integer categoryid) {
		this.category_id = categoryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
