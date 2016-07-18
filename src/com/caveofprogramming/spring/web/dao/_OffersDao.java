package com.caveofprogramming.spring.web.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;



//comment because not use now==>@Component
public class _OffersDao extends DaoResource{
	
	
	public _OffersDao(){}
	
	//I.a query Object
	public Offer getOffer(int id){
		Offer offer = null;
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id",id);
		offer = jdbc.queryForObject("SELECT * FROM offers,users where offers.username=users.username and users.enabled=true and id = :id",params, new OfferRowMapper());
		return offer;		
	}
	
	//I.b query List
	public List<Offer> getOffers(){
		return jdbc.query("SELECT * FROM offers,users where offers.username=users.username and users.enabled=true", new OfferRowMapper());		
	}
	public List<Offer> getOffers(String username){
		return jdbc.query("SELECT * FROM offers,users where offers.username=users.username and users.enabled=true and offers.username = :username",
				new MapSqlParameterSource("username",username), new OfferRowMapper());	
	}
	//II. delete,create, update 
	//II.a 
	public boolean delete(int id){
		MapSqlParameterSource paramSource = new MapSqlParameterSource()
				.addValue("id",id);
		return jdbc.update("DELETE FROM offers WHERE id=:id", paramSource)>0;
	}
	//II.b 
	public boolean create(Offer offer){
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(
				offer);
		
		return jdbc.
				update("insert into offers(username, text) values(:username,:text)", 
						paramMap)==1;
	}
	//II.c
	public boolean update(Offer offer){
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("update offers set text=:text where id=:id", paramMap)==1;
	}
	//IV.a insert, update LIST (by bean property or not)
	
	//@Transactional
	public int[] create(List<Offer> offers){
		SqlParameterSource[] batchValues = SqlParameterSourceUtils.createBatch(offers.toArray());
		return jdbc.batchUpdate("insert into offers(name, text, email) values(:name,:text,:email)", batchValues);
	}
	public int[] update(List<Offer> offers){
		SqlParameterSource[] batchValues = SqlParameterSourceUtils.createBatch(offers.toArray());
		return jdbc.batchUpdate("update offers set name=:name, text=:text, email=:email where id=:id", batchValues);
	}
}
