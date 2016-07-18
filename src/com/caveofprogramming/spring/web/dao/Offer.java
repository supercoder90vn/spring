package com.caveofprogramming.spring.web.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

//, message="This does not appear to be a validate"
@Entity
@Table(name="offers")
public class Offer {
	
	@Size(min=10, max=255, message="Text must be between 10 and 255 characters.")
	private String text;
	
	@Id
	@GeneratedValue//?????
	private int id;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user;	
	
	
	public Offer(){
		this.user = new User();
	}	
	public Offer(User user, String text) {
		this.setUser(user);
		this.text = text;
	}
	public Offer(int id, User user, String text) {
		this.id = id;
		this.setUser(user);
		this.text = text;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public String getUsername(){
		return user.getUsername();
	}
	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Offer [user=" + user + ", text=" + text + ", id=" + id + "]";
	}
	
	
	
	
}
