package com.claim.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="favorites")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Favorites {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
//	@Column(name="user")
//	private User user;
	
	@Column(name="title")
//	@ManyToOne(cascade=CascadeType.ALL)
	private String title;
	
	
//	@Column(name="user")
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_email")
	private User user;
	
	public Favorites() {};

	
//	public Favorites(User user, String title) {
//		this.user = user;
//		this.title = title;
//	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


}
