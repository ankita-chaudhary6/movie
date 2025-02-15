package com.stackroute.moviecruiser.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {

	@Id
	
	private String userId;
	
	private String firstname;
	
	private String lastname;
	
	private String password;
	
	@CreationTimestamp
	private Date created;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User(String userId, String firstname, String lastname, String password, Date created) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.created = created;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
