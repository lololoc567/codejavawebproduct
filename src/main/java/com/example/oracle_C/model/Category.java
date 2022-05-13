package com.example.oracle_C.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Categoryid;
	@Column(nullable = false , length= 64)
	private String Category_name;
	public Long getCategory_id() {
		return Categoryid;
	}
	public void setCategory_id(Long category_id) {
		Categoryid = category_id;
	}
	public String getCategory_name() {
		return Category_name;
	}
	public void setCategory_name(String category_name) {
		Category_name = category_name;
	}
}
