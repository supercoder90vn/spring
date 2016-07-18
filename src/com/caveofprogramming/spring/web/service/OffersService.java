package com.caveofprogramming.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDao;

@Service
public class OffersService {
	
	private OffersDao offersDao;
	
	public List<Offer> getCurrent(){
		return offersDao.getOffers();
	}
	
	@Autowired
	public void setOffersDao(OffersDao offersDao) {
		this.offersDao = offersDao;
	}
	
	public OffersDao getOffersDao() {
		return offersDao;
	}
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	public void create(Offer offer) {
		offersDao.create(offer);
		
	}

	public boolean hasOffer(String username) {		
		return getOffer(username)!=null;
	}

	public Offer getOffer(String username) {
		if(username==null) {
			return null;
		}
		
		List<Offer> offers = offersDao.getOffers(username);
		
		if(offers.size()==0){
			return null;
		}
		
		return offers.get(0);
	}

	public void saveOrUpdate(Offer offer) {	
		offersDao.saveOrUpdate(offer);
	}

	public void delete(int id) {
		offersDao.delete(id);
		
	}
}
