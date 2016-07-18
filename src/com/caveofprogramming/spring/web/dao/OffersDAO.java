package com.caveofprogramming.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
//@Component
public class OffersDao extends DaoResource{
	
	
	public OffersDao(){}
	
	//I.a query Object
	public Offer getOffer(int id){
		Criteria crit = session().createCriteria(Offer.class);
		crit.createAlias("user", "u")
			.add(Restrictions.eq("u.enabled",true))
			.add(Restrictions.idEq(id));
		return (Offer) crit.uniqueResult();			
	}
	
	//I.b query List
	@SuppressWarnings("unchecked")
	public List<Offer> getOffers(){		
		Criteria crit = session().createCriteria(Offer.class);
		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled",true));
		return crit.list();		
	}
	@SuppressWarnings("unchecked")
	public List<Offer> getOffers(String username){
		Criteria crit = session().createCriteria(Offer.class);
		crit.createAlias("user", "u")
			.add(Restrictions.eq("u.enabled",true))
			.add(Restrictions.eq("u.username",username));
		return crit.list();		
	}
	//II. delete,create, update 
	//II.a  delete
	public boolean delete(int id){
		Query query = session().createQuery("delete from Offer where id=:id");
		query.setLong("id", id);
		return query.executeUpdate()==1;
	}
	//II.b create
	public void create(Offer offer){
		session().save(offer);
	}
	//II.c update
	public void saveOrUpdate(Offer offer){
		/*if(offer.getId()!= 0){
			session().update(offer);
		}else{
			session().create(offer);
		}*/
		session().saveOrUpdate(offer);
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
