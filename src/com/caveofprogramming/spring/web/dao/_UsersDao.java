package com.caveofprogramming.spring.web.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


//comment because not use now ===> @Component
public class _UsersDao extends DaoResource {
	@Autowired
	private PasswordEncoder passwordEncoder;
	public _UsersDao(){}	
	@Transactional
	public boolean create(User user){
		//BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(user);
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("username", user.getUsername());		
		paramMap.addValue("password",passwordEncoder.encode(user.getPassword()) );
		paramMap.addValue("email", user.getEmail());
		paramMap.addValue("name", user.getName());
		paramMap.addValue("enabled", user.isEnabled());		
		paramMap.addValue("authority", user.getAuthority());
		return jdbc.update("insert into users(username, name, password, email,enabled,authority) values(:username,:name,:password,:email,:enabled,:authority)", paramMap)==1;
		
	}
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username", 
				new MapSqlParameterSource("username",username),Integer.class)>0;
	}
	
	public List<User> getAllUsers() {
		return jdbc.query("select * from users", BeanPropertyRowMapper.newInstance(User.class));
	}
}
