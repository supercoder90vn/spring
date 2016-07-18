package com.caveofprogramming.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class MessagesDao extends DaoResource{
	
	
	public MessagesDao(){}
	
	//I.a query Object
	public Message getMessage(int id){
		Criteria crit = session().createCriteria(Message.class)
				.add(Restrictions.idEq(id));
		return (Message) crit.uniqueResult();			
	}
	
	//I.b query List
	@SuppressWarnings("unchecked")
	public List<Message> getMessages(){		
		Criteria crit = session().createCriteria(Message.class);
		return crit.list();		
	}
	@SuppressWarnings("unchecked")
	public List<Message> getMessages(String username){
		Criteria crit = session().createCriteria(Message.class)
			.add(Restrictions.eq("username",username));
		return crit.list();		
	}
	//II. delete,create, update 
	//II.a  delete
	public boolean delete(int id){
		Query query = session().createQuery("delete from Message where id=:id");
		query.setLong("id", id);
		return query.executeUpdate()==1;
	}
	//II.b create
	public void create(Message message){
		session().save(message);
	}
	//II.c update
	public void saveOrUpdate(Message message){
		System.out.println("__saveOrUpdate: "+message);
		session().saveOrUpdate(message);
	}
}
