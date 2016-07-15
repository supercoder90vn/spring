package com.caveofprogramming.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;


@Component
public class OffersDAO {
	private NamedParameterJdbcTemplate jdbc;
	public OffersDAO(){
		System.out.println("OffersDAO()");
	}
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbc= new NamedParameterJdbcTemplate(dataSource);
	}
	//I.a query Object
	public Offer getOffer(int id){
		Offer offer = null;
		//try{
			MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("id","1");
			offer = jdbc.queryForObject("SELECT * FROM offers WHERE id = :id",params, new OfferMapper());
		//}catch(EmptyResultDataAccessException e){
		//	offer = null;
		//}
		
		
		return offer;		
	}
	//I.b query List
	public List<Offer> getOffers(){
		return jdbc.query("SELECT * FROM offers", new OfferMapper());		
	}
	//II.b delete,create, update (by bean property or not)
	public boolean delete(int id){
		MapSqlParameterSource paramSource = new MapSqlParameterSource()
				.addValue("id","1");
		return jdbc.update("DELETE FROM offers WHERE id=:id", paramSource)>0;
	}
	public boolean create(Offer offer){
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("insert into offers(name, text, email) values(:name,:text,:email)", paramMap)==1;
	}
	public boolean update(Offer offer){
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("update offers set name=:name, text=:text, email=:email where id=:id", paramMap)==1;
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
	//__________________________________________________________________
	private static final class OfferMapper implements RowMapper<Offer>{
		public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {			
			//System.out.println(dataSource); => cannot not use if nested class is static
			Offer offer = new Offer();
			offer.setId(rs.getInt("id"));
			offer.setName(rs.getString("name"));
			offer.setText(rs.getString("text"));
			offer.setEmail(rs.getString("email"));
			return offer;
		}		
	}
}
