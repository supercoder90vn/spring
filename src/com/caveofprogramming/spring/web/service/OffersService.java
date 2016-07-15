package com.caveofprogramming.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDAO;

@Service
public class OffersService {
	
	private OffersDAO offersDao;
	
	public List<Offer> getCurrent(){
		return offersDao.getOffers();
	}
	
	@Autowired
	public void setOffersDao(OffersDAO offersDao) {
		this.offersDao = offersDao;
	}
	
	public OffersDAO getOffersDao() {
		return offersDao;
	}

	public void create(Offer offer) {
		offersDao.create(offer);
		
	}

	public void throwTestException() {
		offersDao.getOffer(99999);
		
	}

	
	
	
}
