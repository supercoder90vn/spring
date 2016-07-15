package com.caveofprogramming.spring.web.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.caveofprogramming.spring.web.validation.ValidEmail;
//, message="This does not appear to be a validate"
public class Offer {
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Size(min=5, max=100, message="Name must be between 5 and 100 characters.")
	private String name;
	
	@NotNull
	//@Pattern(regexp=EMAIL_PATTERN,message="This does not appear to be a valid email address")	
	@ValidEmail(min=6, message="this email address is not valid.")
	private String email;
	
	@Size(min=10, max=255, message="Text must be between 10 and 255 characters.")
	private String text;
	
	private int id;
	public Offer(){}
	
	
	public Offer(String name, String email, String text) {
		this.name = name;
		this.email = email;
		this.text = text;
	}
	public Offer(int id, String name, String email, String text) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.text = text;
	}



	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", email=" + email + ", text=" + text + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
