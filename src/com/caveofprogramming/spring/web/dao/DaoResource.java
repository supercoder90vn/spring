package com.caveofprogramming.spring.web.dao;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
/** If child class is required to extend other parent class. Then 
 * copy all field and method in this class to use directly
 * */
public class DaoResource {
	//I. hibernate
	@Autowired
	private SessionFactory sessionFactory;
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	//II. jdbc
	protected NamedParameterJdbcTemplate jdbc;
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbc= new NamedParameterJdbcTemplate(dataSource);
	}
}
