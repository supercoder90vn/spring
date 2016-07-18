package com.caveofprogramming.spring.web.dao;


import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Transactional
@Repository
//@Component
public class UsersDao extends DaoResource{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public UsersDao(){}
	
	
	@Transactional
	public void create(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);		
	}
	public User getUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username",username));
		return (User) crit.uniqueResult();
	}
	public boolean exists(String username) {		
		return getUser(username)!=null;
	}
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Query query =  session().createQuery("from User");
		return query.list();
	}
}
